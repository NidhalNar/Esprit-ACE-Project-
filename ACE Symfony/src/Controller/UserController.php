<?php

namespace App\Controller;

use App\Entity\User;
use App\Form\UserType;
use App\Repository\UserRepository;
use App\Form\ChangePasswordFormType;
use Doctrine\Persistence\ManagerRegistry;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\PasswordHasher\Hasher\UserPasswordHasherInterface;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Knp\Component\Pager\PaginatorInterface;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;


#[Route('/user')]
class UserController extends AbstractController
{
    #[Route('/', name: 'app_admin', methods: ['GET'])]
    public function index(UserRepository $userRepository): Response
    {  
        return $this->render('user/index.html.twig', [
            'users' => $userRepository->findAll(),
        ]);
    }
    #[Route('/front', name: 'app_user_front', methods: ['GET'])]
    public function indexf(UserRepository $userRepository): Response
    {
        return $this->render('user/indexFront.html.twig', [
            'users' => $userRepository->findAll(),
        ]);
    }

    #[Route('/front/{id}/edit', name: 'app_userf_front', methods:  ['GET', 'POST'])]
    public function editf(Request $request, User $user, UserRepository $userRepository, ManagerRegistry $doctrine): Response
    {     
        $form = $this->createForm(UserType::class, $user);
        $form->handleRequest($request); 

        if ($form->isSubmitted() && $form->isValid()) {
            $userRepository->save($user, true);


            $doctrine->getManager()->flush(); //sauvgarde les changements
            toastr()
                ->escapeHtml(false)
                ->addSuccess('Votre profil a été modifié avec succès.');

            return $this->redirectToRoute('app_user_front', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('user/editFront.html.twig', [
            'user' => $user,
            'form' => $form,
        ]);
        
    }
    #[Route('/front/{id}/editpassword', name: 'app_user_editpasswordFront', methods: ['GET', 'POST'])]
    public function editPasswordFront(Request $request, UserPasswordHasherInterface $userPasswordHasher, User $user,EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(ChangePasswordFormType::class);
        $form->handleRequest($request);
 
        if ($form->isSubmitted() && $form->isValid()) {
            $data = $form->getData();
 
            $user->setPassword(
                $userPasswordHasher->hashPassword(
                    $user,
                    $form->get('plainPassword')->getData()
                )
            );
 
            $user->setRoles(['ROLE_USER']);
 
            $entityManager->persist($user);
            $entityManager->flush();
            toastr()
            ->escapeHtml(false)
            ->addSuccess('Votre mot de passe a été modifiée avec succès.');
                return $this->redirectToRoute('app_home', ['id' => $user->getId()]);
 
        }
 
        return $this->render('user/editpassword.html.twig', [
            'form' => $form->createView(),
        ]);
    }
 
    #[Route('/listU', name: 'app_user_listU', methods: ['GET'])]
    public function listU(UserRepository $userRepository,PaginatorInterface $paginator, Request $request): Response
    {
        $users = $userRepository->createQueryBuilder('u')
            ->andWhere('u.isVerified = :verified')
            ->andWhere('u.roles LIKE :roles')
            ->setParameter('verified', true)
            ->setParameter('roles', '%"ROLE_USER"%')
            ->getQuery()
            ->getResult();

            $users = $paginator->paginate( //contient le résultat de la requête qui sera paginé
                //une instance de la classe Paginator de KnpPaginatorBundle
                $users, /* query NOT result */
                $request->query->getInt('page', 1), /*page number*/
                3/*limit per page*/
            );
        return $this->render('user/listU.html.twig', [
            'users' => $users,
        ]);
    }
    
    
    #[Route('/listUNV', name: 'app_user_listUNV', methods: ['GET'])]
    public function listUN(UserRepository $userRepository,PaginatorInterface $paginator, Request $request): Response
    {
       
        $user = $userRepository->findBy(['isVerified'=>false]);

        $user = $paginator->paginate(
            $user, /* query NOT result */
            $request->query->getInt('page', 1), /*page number*/
            3/*limit per page*/
        );
        return $this->render('user/listUNV.html.twig', [
            'users' => $user,
        ]);
    }

    #[Route('/listBlocked', name: 'app_user_listBlocked', methods: ['GET'])]
public function listBlockedUsers(UserRepository $userRepository,PaginatorInterface $paginator, Request $request): Response
{
    $users = $userRepository->createQueryBuilder('u')// Crée une instance de QueryBuilder qui cible entité user
        ->andWhere('u.isVerified = :verified')
        ->andWhere('u.roles LIKE :roles')
        ->setParameter('verified', true)
        ->setParameter('roles', '%"BLOCKED"%')
        ->getQuery() //Récupère la requête créée jusqu'à présent sous forme d'objet Query
        ->getResult();// Exécute la requête et retourne les résultats sous forme d'un tableau d'objets User

       
            $users = $paginator->paginate(
                $users, /* query NOT result */
                $request->query->getInt('page', 1), /*page number*/ 
                3/*limit per page*/
            );

    return $this->render('user/listBlocked.html.twig', [
        'users' => $users,
    ]);
}


    #[Route('/desactiverUser/{id}', name: 'app_user_desactiver')]
    public function desactiverUser(ManagerRegistry $doctrine, $id)
    {
        $user = $doctrine->getRepository(User::class)->find($id);
        $user->setIsVerified(0);
        $entityManager = $doctrine->getManager();

        $entityManager->persist($user); // commencer à gérer l'entité $user
        $entityManager->flush(); 
        //exécute toutes les requêtes SQL nécessaires pour synchroniser les entités gérées avec la base de données
        toastr()
            ->escapeHtml(false)
            ->addWarning('Ce compte  est désactivé.');
        return $this->redirectToRoute('app_user_listUNV');
    }

    #[Route('/activerUser/{id}', name: 'app_user_activer')]
    public function activeruser(ManagerRegistry $doctrine, $id)
    {
        $user = $doctrine->getRepository(User::class)->find($id);
        $user->setIsVerified(1);
        $entityManager = $doctrine->getManager();

        $entityManager->persist($user);
        $entityManager->flush();
        toastr()
            ->escapeHtml(false)
            ->addSuccess('Ce compte  est activé avec succès.');
        return $this->redirectToRoute('app_user_listU');
    }

    
    #[Route('/ban/{id}', name: 'app_user_ban', methods: ['GET'])]
    public function ban(User $u)
    {
        $em = $this->getDoctrine()->getManager();
        $u->setRoles(['BLOCKED']);
        $em->flush();// Executer la requête et d’envoyer tout ce qui à été persisté avant a la BD
        toastr()
        ->escapeHtml(false)
        ->addWarning('Ce compte est bloqué.');
        return $this->redirectToRoute('app_user_listBlocked');
    }
   
    #[Route('/unban/{id}', name: 'app_user_unban', methods: ['GET'])]

    public function unban(User $u)
    {
        $em = $this->getDoctrine()->getManager();
        $u->setRoles(["ROLE_USER"]);
        $em->flush();
        toastr()
        ->escapeHtml(false)
        ->addSuccess('Ce compte est débloqué avec succès.');
        return $this->redirectToRoute('app_user_listU');
    }
    
    #[Route('/{id}', name: 'app_user_show', methods: ['GET'])]
    public function show(User $user): Response
    {
        return $this->render('user/show.html.twig', [
            'user' => $user,
        ]);
    }

  #[Route('/{id}/edit', name: 'app_user_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, User $user, UserRepository $userRepository, ManagerRegistry $doctrine): Response
    {
         $form = $this->createForm(UserType::class, $user);
         
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $userRepository->save($user, true);

            $doctrine->getManager()->flush();
         toastr()
                ->escapeHtml(false)
                ->addSuccess('Votre profil a été modifié avec succès.');
            return $this->redirectToRoute('app_user_listU', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('user/edit.html.twig', [
            'user' => $user,
            'form' => $form->createView(),
        ]);

    }
   
    #[Route('/deleteU/{id}', name: 'app_user_delete')]
    public function deleteU(ManagerRegistry $doctrine, $id) {
        $user = $doctrine->getRepository(User::class)->find($id);
        $entityManager = $doctrine->getManager();
        $entityManager->remove($user);
        $entityManager->flush();
        toastr()
            ->escapeHtml(false)
            ->addSuccess('Ce utilisateur a été supprimer avec succès..');
        return $this->redirectToRoute('app_user_listU');
    }
    #[Route('/user/statistique', name: 'app_statistique')]
    public function stat()
    {
        $repository = $this->getDoctrine()->getRepository(User::class);
        $users = $repository->findAll();
    
        $verifiedCount = 0;
        $notVerifiedCount = 0;

    /// parcourir tous les utilisateurs
        foreach ($users as $user) {
            if ($user->isVerified() == 1) {
                $verifiedCount++;
            } else {
                $notVerifiedCount++;
            }
        }
    // calcule le nombre total d'utilisateurs.
        $total = $verifiedCount + $notVerifiedCount;
    
        $verifiedPercentage = round($verifiedCount / $total * 100, 2);
        $notVerifiedPercentage = round($notVerifiedCount / $total * 100, 2);
    
        $pieChart = new PieChart(); //Objet
        $pieChart->getData()->setArrayToDataTable([
            ['Task', 'Percentage'],
            ['Vérifié', $verifiedPercentage],
            ['Non Vérifié', $notVerifiedPercentage]
        ]);
        $pieChart->getOptions()->setHeight(500);
        $pieChart->getOptions()->setWidth(900);
    //    $pieChart->getOptions()->getTitleTextStyle()->setBold(true);
    //    $pieChart->getOptions()->getTitleTextStyle()->setColor('#2e4b71');
    //    $pieChart->getOptions()->getTitleTextStyle()->setItalic(false);
    //    $pieChart->getOptions()->getTitleTextStyle()->setFontName('Helvetica');
    //    $pieChart->getOptions()->getTitleTextStyle()->setFontSize(25);
    
        return $this->render('user/statistique.html.twig', array('piechart' => $pieChart));
    }
    
}
