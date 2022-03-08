<?php

namespace App\Controller;

use App\Entity\Commande;
use App\Entity\LigneCommande;
use App\Entity\Produit;
use App\Repository\ProduitRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\ORM\EntityManagerInterface;
use App\Repository\CommandeRepository;

/**
 * @Route("/cart", name="cart_")
 */
class CartController extends AbstractController
{
    /**
     * @Route("/", name="index")
     */
    public function index(SessionInterface $session, ProduitRepository $produitRepository)
    {
        $panier = $session->get("panier", []);

        // On "fabrique" les données
        $dataPanier = [];
        $total = 0;

        foreach($panier as $id => $quantite){
            $produit = $produitRepository->find($id);
            $dataPanier[] = [
                "produit" => $produit,
                "quantite" => $quantite
            ];
            $total += $produit->getPrixProduit() * $quantite;
        }

        return $this->render('cart/index.html.twig', compact("dataPanier", "total"));
    }

    /**
     * @Route("/add/{id}", name="add")
     */
    public function add($id, SessionInterface $session)
    {
        // On récupère le panier actuel
        $panier = $session->get("panier", []);


        if(!empty($panier[$id])){
            $panier[$id]++;
        }else{
            $panier[$id] = 1;
        }

        // On sauvegarde dans la session
        $session->set("panier", $panier);

        return $this->redirectToRoute("cart_index");
    }

    /**
     * @Route("/remove/{id}", name="remove")
     */
    public function remove($id, SessionInterface $session)
    {
        // On récupère le panier actuel
        $panier = $session->get("panier", []);


        if(!empty($panier[$id])){
            if($panier[$id] > 1){
                $panier[$id]--;
            }else{
                unset($panier[$id]);
            }
        }

        // On sauvegarde dans la session
        $session->set("panier", $panier);

        return $this->redirectToRoute("cart_index");
    }

    /**
     * @Route("/delete/{id}", name="delete")
     */
    public function delete($id, SessionInterface $session)
    {
        // On récupère le panier actuel
        $panier = $session->get("panier", []);

        if(!empty($panier[$id])){
            unset($panier[$id]);
        }

        // On sauvegarde dans la session
        $session->set("panier", $panier);

        return $this->redirectToRoute("cart_index");
    }

    /**
     * @Route("/delete", name="delete_all")
     */
    public function deleteAll(SessionInterface $session)
    {
        $session->remove("panier");

        return $this->redirectToRoute("cart_index");
    }


  /**
     * @Route("/save", name="save")
     */
    public function save(SessionInterface $session,ProduitRepository $produitRepository,EntityManagerInterface $entityManager,CommandeRepository $CommandeRepository)
    {
        // On récupère le panier actuel
        $panier = $session->get("panier", []);
        //dd($panier);
        $total = 0;
        $p = 0;

            foreach($panier as $id => $quantite) {
                $commande = $produitRepository->find($id);
                $dataPanier[] = [
                    "produit" => $commande,
                    "quantite" => $quantite
                ];
            }

               // $total += $commande->getPrixProduit() * $quantite;
               // $quan = $quantite;
                //$p = $commande->getPrixProduit();
                $com = new Commande();
                //$com->setProduit($commande);
                $com->setNumero(15);
                $com->setIdUser(1);
                //$com->setPrixCommande($p);
                $com->setDate(new \DateTime('@'.strtotime('now')));
                $com->setTotale($total);
                //$com->setQuantite($quan);



                $entityManager->persist($com);


        $entityManager->flush();
        $idCommande=$CommandeRepository->find($com->getId());
        foreach($panier as $id => $quantite) {
            $commande = $produitRepository->find($id);
            $dataPanier[] = [
                "produit" => $commande,
                "quantite" => $quantite
            ];

            $l = new LigneCommande();
            $l->setQuantite($quantite);
            $l->setProduit($commande);
            $l->setCommande($idCommande);

            $entityManager->persist($l);


            $entityManager->flush();
        }


        // On sauvegarde dans la session
        $session->set("panier", []);

        return $this->redirectToRoute("afficherproduitClient");
    }
}
