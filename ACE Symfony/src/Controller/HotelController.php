<?php

namespace App\Controller;

use App\Entity\Hotel;
use App\Entity\Destination;
use App\Form\HotelType;
use App\Repository\HotelRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use PhpOffice\PhpSpreadsheet\IOFactory;
use PhpOffice\PhpSpreadsheet\Spreadsheet;
use PhpOffice\PhpSpreadsheet\Writer\Xlsx;
use Symfony\Component\HttpFoundation\Session\Flash\FlashBagInterface;
use Symfony\Component\HttpFoundation\ResponseHeaderBag;
use Endroid\QrCode\Builder\Builder;
use Endroid\QrCode\Encoding\Encoding;
use Endroid\QrCode\ErrorCorrectionLevel\ErrorCorrectionLevelHigh;
use Endroid\QrCode\Label\Alignment\LabelAlignmentCenter;
use Endroid\QrCode\Label\Margin\Margin;
use Endroid\QrCode\Writer\PngWriter;
use Symfony\Component\HttpFoundation\BinaryFileResponse;
use Endroid\QrCode\Label\Font\NotoSans;
use Endroid\QrCode\RoundBlockSizeMode\RoundBlockSizeModeMargin;
use Endroid\QrCode\Builder\BuilderInterface; // Add this "use" statement
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Component\HttpFoundation\JsonResponse;





#[Route('/hotel')]
class HotelController extends AbstractController
{
    #[Route('/', name: 'app_hotel_index', methods: ['GET'])]
    public function index(HotelRepository $hotelRepository): Response
    {
        return $this->render('hotel/index.html.twig', [
            'hotels' => $hotelRepository->findAll(),
        ]);
    }

    #[Route('/front', name: 'app_hotel_index2', methods: ['GET'])]
    public function front(HotelRepository $hotelRepository,PaginatorInterface $paginator,Request $request): Response
    {
        $query = $hotelRepository->createQueryBuilder('s')
        ->orderBy('s.nom', 'ASC')
        ->getQuery();
        $paginatedhotel = $paginator->paginate(
            $query,
            $request->query->getInt('page', 1),4 // Number of results per page
        );
        return $this->render('hotel_front/index.html.twig', [
            'hotels' => $paginatedhotel,
            'pagination' => $paginatedhotel,
        ]);
    }

    #[Route('/new', name: 'app_hotel_new', methods: ['GET', 'POST'])]
    public function new(Request $request, HotelRepository $hotelRepository): Response
    {
        $hotel = new Hotel();
        $form = $this->createForm(HotelType::class, $hotel);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $hotelRepository->save($hotel, true);
            $this->addFlash('success', 'Hotel added successfully!');

            return $this->redirectToRoute('app_hotel_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('hotel/new.html.twig', [
            'hotel' => $hotel,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_hotel_show', methods: ['GET'])]
    public function show(Hotel $hotel): Response
    {
        return $this->render('hotel/show.html.twig', [
            'hotel' => $hotel,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_hotel_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Hotel $hotel, HotelRepository $hotelRepository): Response
    {
        $originalData = clone $hotel;
        $form = $this->createForm(HotelType::class, $hotel);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $hotelRepository->save($hotel, true);
            // Check if any changes were made to the form
        if ($hotel != $originalData) {
            $this->addFlash('success', 'hotel modified successfully!');
            
        } else {
            $this->addFlash('error', 'No changes made to hotel.');
            
        }

            return $this->redirectToRoute('app_hotel_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('hotel/edit.html.twig', [
            'hotel' => $hotel,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_hotel_delete', methods: ['POST'])]
    public function delete(Request $request, Hotel $hotel, HotelRepository $hotelRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$hotel->getId(), $request->request->get('_token'))) {
            $hotelRepository->remove($hotel, true);
            $this->addFlash('success', 'Hotel deleted successfully!');
        }

        return $this->redirectToRoute('app_hotel_index', [], Response::HTTP_SEE_OTHER);
    }



    public function exportHotelsToExcel()
{
    // Fetch the hotels from your database or wherever you store them
    $hotels = $this->getDoctrine()->getRepository(Hotel::class)->findAll();

    // Create a new Spreadsheet object
    $spreadsheet = new Spreadsheet();
    $sheet = $spreadsheet->getActiveSheet();
    $sheet->setCellValue('A1', 'Nom');
    $sheet->setCellValue('B1', 'Etoile');
    $sheet->setCellValue('C1', 'Type');
    $sheet->setCellValue('D1', 'Destination');

    // Loop through the hotels and write the nom, etoile, type, and destination properties to the spreadsheet
    $row = 2;
    foreach ($hotels as $hotel) {
        $sheet->setCellValue('A' . $row, $hotel->getNom());
        $sheet->setCellValue('B' . $row, $hotel->getEtoile().' Stars');
        $sheet->setCellValue('C' . $row, $hotel->getType());
        $sheet->setCellValue('D' . $row, $hotel->getPlace()->getPays() . ', ' . $hotel->getPlace()->getVille());
        $row++;
    }

    // Create a new Xlsx writer and save the spreadsheet to a file
    $writer = new Xlsx($spreadsheet);
    $filename = 'hotels.xlsx';
    $writer->save($filename);

    // Send the Excel file as a response
    $response = new Response(file_get_contents($filename));
    $disposition = $response->headers->makeDisposition(
        ResponseHeaderBag::DISPOSITION_ATTACHMENT,
        $filename
    );
    $response->headers->set('Content-Disposition', $disposition);
    unlink($filename); // Delete the temporary file
    return $response;
}

public function generateQrCodehotel($id)
{
    // Get the row's data from the table based on the $id
    $hotel = $this->getDoctrine()->getRepository(Hotel::class)->find($id); // Update with your actual entity name and repository method

    if (!$hotel) {
        throw $this->createNotFoundException('Hotel not found');
    }

    // Generate QR code with the row's data
    $result = Builder::create() // Update to use BuilderInterface instead of Builder
        ->writer(new PngWriter())
        ->data("Hotel: " . $hotel->getNom() . 
            ' Located At - ' . $hotel->getPlace()->getPays() . 
            ' , ' . $hotel->getPlace()->getVille() . 
            ' - With  ' . $hotel->getEtoile() . 
            ' Stars - Type: ' . $hotel->getType()
        )
        ->encoding(new Encoding('UTF-8'))
        ->errorCorrectionLevel(new ErrorCorrectionLevelHigh())
        ->size(300)
        ->margin(10)
        ->labelText("")
        ->labelAlignment(new LabelAlignmentCenter())
        ->labelMargin(new Margin(15, 5, 5, 5))
        ->build();

    $namePng = uniqid('', '') . '.png';
    $result->saveToFile($this->getParameter('kernel.project_dir') . '/public/back-office/assets/img/qrcode' . $namePng); // Update with the actual path to your QR code folder

    // Return a response with the QR code image as a download
    $response = new Response();
    $response->headers->set('Content-Type', 'image/png');
    $response->headers->set('Content-Disposition', 'attachment; filename="' . $namePng . '"');
    $response->setContent(file_get_contents($this->getParameter('kernel.project_dir') . '/public/back-office/assets/img/qrcode' . $namePng));
    return $response;
}





    
   


    
    

}
