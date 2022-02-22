<?php

namespace App\Controller;

use App\Repository\CommandeRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class CommandeController extends AbstractController
{
    /**
     * @Route("/commande", name="commande")
     */
    public function index(): Response
    {
        return $this->render('commande/index.html.twig', [
            'controller_name' => 'CommandeController',
        ]);
    }

    /**
     * @Route("/suppcommande/{id}",name="suppcommande")
     */
    public function suppcommande($id,EntityManagerInterface $em ,CommandeRepository $repository){
        $cours=$repository->find($id);
        $em->remove($cours);
        $em->flush();

        return $this->redirectToRoute('affichercommande');
    }


    /**
     * @Route("/affichercommande",name="affichercommande")
     */
    public function Affichercommande(CommandeRepository $repository){
        $tablecommandes=$repository->findAll();
        return $this->render('produit/afficherCommande.html.twig'
            ,['tablecommandes'=>$tablecommandes]);

    }



}
