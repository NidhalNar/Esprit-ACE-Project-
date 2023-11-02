<?php

namespace App\Controller;

use App\Entity\CategoriesSponsor;
use App\Form\CategoriesSponsorType;
use App\Repository\CategoriesSponsorRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/categories/sponsor')]
class CategoriesSponsorController extends AbstractController
{
    #[Route('/', name: 'app_categories_sponsor_index', methods: ['GET'])]
    public function index(CategoriesSponsorRepository $categoriesSponsorRepository): Response
    {
        return $this->render('categories_sponsor/index.html.twig', [
            'categories_sponsors' => $categoriesSponsorRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_categories_sponsor_new', methods: ['GET', 'POST'])]
    public function new(Request $request, CategoriesSponsorRepository $categoriesSponsorRepository): Response
    {
        $categoriesSponsor = new CategoriesSponsor();
        $form = $this->createForm(CategoriesSponsorType::class, $categoriesSponsor);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $categoriesSponsorRepository->save($categoriesSponsor, true);
            $this->addFlash('success', 'New Categotrie added successfully!');


            return $this->redirectToRoute('app_categories_sponsor_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('categories_sponsor/new.html.twig', [
            'categories_sponsor' => $categoriesSponsor,
            'form' => $form,
        ]);
    }

    #[Route('/{id_cat}', name: 'app_categories_sponsor_show', methods: ['GET'])]
    public function show(CategoriesSponsor $categoriesSponsor): Response
    {
        return $this->render('categories_sponsor/show.html.twig', [
            'categories_sponsor' => $categoriesSponsor,
        ]);
    }

    #[Route('/{id_cat}/edit', name: 'app_categories_sponsor_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, CategoriesSponsor $categoriesSponsor, CategoriesSponsorRepository $categoriesSponsorRepository): Response
    {
        $form = $this->createForm(CategoriesSponsorType::class, $categoriesSponsor);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $categoriesSponsorRepository->save($categoriesSponsor, true);

            return $this->redirectToRoute('app_categories_sponsor_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('categories_sponsor/edit.html.twig', [
            'categories_sponsor' => $categoriesSponsor,
            'form' => $form,
        ]);
    }

    #[Route('/{id_cat}', name: 'app_categories_sponsor_delete', methods: ['POST'])]
    public function delete(Request $request, CategoriesSponsor $categoriesSponsor, CategoriesSponsorRepository $categoriesSponsorRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$categoriesSponsor->getId_cat(), $request->request->get('_token'))) {
            $categoriesSponsorRepository->remove($categoriesSponsor, true);
        }

        return $this->redirectToRoute('app_categories_sponsor_index', [], Response::HTTP_SEE_OTHER);
    }
}
