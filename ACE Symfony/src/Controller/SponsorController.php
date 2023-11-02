<?php

namespace App\Controller;

use App\Entity\Sponsor;
use App\Form\SponsorType;
use App\Repository\SponsorRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\ResponseHeaderBag;




use Knp\Component\Pager\PaginatorInterface;

use Twilio\Rest\Client;

use Dompdf\Dompdf;
use Dompdf\Options;

#[Route('/sponsor')]
class SponsorController extends AbstractController


{


    #[Route('/', name: 'app_sponsor_index', methods: ['GET'])]
    public function index(SponsorRepository $sponsorRepository, PaginatorInterface $paginator, Request $request): Response
    {
        $query = $sponsorRepository->createQueryBuilder('s')
            ->orderBy('s.intitule', 'ASC')
            ->getQuery();
        
        $paginatedsponsor = $paginator->paginate(
            $query,
            $request->query->getInt('page', 1),
            4 // Number of results per page
        );
        
        return $this->render('sponsor/index.html.twig', [
            'sponsors' => $paginatedsponsor, // Use the paginated results instead of all results
            'pagination' => $paginatedsponsor,
        ]);
    }

   

    #[Route('/front', name: 'app_sponsor_index1', methods: ['GET'])]
    public function index1(SponsorRepository $sponsorRepository, PaginatorInterface $paginator, Request $request): Response
    {
        $query = $sponsorRepository->createQueryBuilder('s')
        ->orderBy('s.intitule', 'ASC')
        ->getQuery();
    
    $paginatedsponsor = $paginator->paginate(
        $query,
        $request->query->getInt('page', 1),
        4 // Number of results per page
    );
    
    return $this->render('front_sponsor/index.html.twig', [
        'sponsors' => $paginatedsponsor, // Use the paginated results instead of all results
        'pagination' => $paginatedsponsor,
    ]);
    }

     
      #[Route('/pdf', name: 'app_pdf', methods: ['GET'])]
      public function pdf(SponsorRepository $SponsorRepository)
      {
          $pdfOptions = new Options();
          $pdfOptions->set('defaultFont', 'Arial');
  
          $dompdf = new Dompdf($pdfOptions);
          $html = $this->renderView('sponsor/pdf.html.twig', [
              'sponsors' => $SponsorRepository->findAll(),
          ]);
  
          $dompdf->loadHtml($html);
          $dompdf->setPaper('A4', 'portrait');
  
          $dompdf->render();
          $dompdf->stream("ListeDesSponsors.pdf", [
              "sponsors" => true
          ]);
      }
      




    #[Route('/new', name: 'app_sponsor_new', methods: ['GET', 'POST'])]
    public function new(Request $request, SponsorRepository $sponsorRepository): Response
    {
        $sponsor = new Sponsor();
        $form = $this->createForm(SponsorType::class, $sponsor);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $sponsorRepository->save($sponsor, true);
            $this->addFlash('success', 'New Sponsor added successfully!');

            $accountSid = 'ACb3512b1baa60eb994049eea0656f6934';
            $authToken = 'db782f1385c2a4a273b50e7c3222a46f';
            $client = new Client($accountSid, $authToken);
    
            $message = $client->messages->create(
                '+21621560477', // replace with admin's phone number
                [
                    'from' => '+15673343461', // replace with your Twilio phone number
                    'body' => 'A new sponsor ' . $form->get('intitule')->getData() .'has been successfully added ' ,
                ]

            );


            return $this->redirectToRoute('app_sponsor_index', [], Response::HTTP_SEE_OTHER);

        }

        return $this->renderForm('sponsor/new.html.twig', [
            'sponsor' => $sponsor,
            'form' => $form,
        ]);
    }

    #[Route('/{id_sponsor}', name: 'app_sponsor_show', methods: ['GET'])]
    public function show(Sponsor $sponsor): Response
    {
        return $this->render('sponsor/show.html.twig', [
            'sponsor' => $sponsor,
        ]);
    }

    #[Route('/{id_sponsor}/edit', name: 'app_sponsor_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Sponsor $sponsor, SponsorRepository $sponsorRepository): Response
    {
        $form = $this->createForm(SponsorType::class, $sponsor);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $sponsorRepository->save($sponsor, true);

            return $this->redirectToRoute('app_sponsor_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('sponsor/edit.html.twig', [
            'sponsor' => $sponsor,
            'form' => $form,
        ]);
    }

    #[Route('/{id_sponsor}', name: 'app_sponsor_delete', methods: ['POST'])]
    public function delete(Request $request, Sponsor $sponsor, SponsorRepository $sponsorRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$sponsor->getId_sponsor(), $request->request->get('_token'))) {
            $sponsorRepository->remove($sponsor, true);
        }

        return $this->redirectToRoute('app_sponsor_index', [], Response::HTTP_SEE_OTHER);
    }

  



#[Route('/search', name: 'app_sponsor_search"')]
public function search(Request $request, NormalizerInterface $Normalizer,  SponsorRepository $sr)
{
    $repository = $this->getDoctrine()->getRepository(Sponsor::class);
    $requestString = $request->get('searchValue');
    $sponsors = $repository->findBySearchQuery($requestString);
    $jsonContent = $Normalizer->normalize($sponsors, 'json', ['groups' => 'sponsor']);
    $retour = json_encode($jsonContent);
    return new Response($retour);
}




}
