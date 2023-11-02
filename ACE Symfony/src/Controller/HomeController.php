<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;
class HomeController extends AbstractController
{
    #[Route('/home', name: 'app_home')]
    public function index(): Response
    {
        return $this->render('home/index.html.twig', [
            'controller_name' => 'HomeController',
        ]);
    }
    #[Route('/', name: 'app')]
    public function index1(): Response
    {
        return $this->render('home/index1.html.twig', [
            'controller_name' => 'HomeController',
        ]);
    }
   #[Route('/not', name: 'app_notverified')]
public function notverifed(): Response
{

    return $this->render('home/notverified.html.twig', [
        'controller_name' => 'HomeController',
    ]);
}
#[Route('/blocked', name: 'app_blocked')]
public function blocked(): Response
{

    return $this->render('home/blocked.html.twig', [
        'controller_name' => 'HomeController',
    ]);
}

}
