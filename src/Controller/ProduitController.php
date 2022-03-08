<?php

namespace App\Controller;


use App\Entity\Commande;
use App\Entity\Produit;
use App\Form\ProduitType;
use App\Repository\CommandeRepository;
use App\Repository\HotelRepository;
use App\Repository\ProduitRepository;
use Doctrine\ORM\EntityManagerInterface;
use Dompdf\Dompdf;
use Dompdf\Options;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ProduitController extends AbstractController
{


    /**
         * @Route("/afficherproduit",name="afficherproduit")
     */
    public function Affiche(Request $request,ProduitRepository $repository,PaginatorInterface $paginator){
        $tableprduits=$repository->listproduitparprix();
        $tableprduits = $paginator->paginate(
            $tableprduits,
            $request->query->getInt('page', 1),
            4
        );

        return $this->render('produit/afficherProduits.html.twig'
            ,['tableproduits'=>$tableprduits]);

    }




    /**
     * @Route("/ajoutproduit",name="ajoutproduit")
     */
    public function ajouterProduit(EntityManagerInterface $em,Request $request ,ProduitRepository $UserRepository,\Swift_Mailer $mailer ){
        $produit= new Produit();
        $form= $this->createForm(ProduitType::class,$produit);
        $form->add('Ajouter',SubmitType::class);
        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid()){
            $new=$form->getData();
            $imageFile = $form->get('imgproduit')->getData();
            if ($imageFile) {
                $originalFilename = pathinfo($imageFile->getClientOriginalName(), PATHINFO_FILENAME);
                $newFilename = $originalFilename.'-'.uniqid().'.'.$imageFile->guessExtension();
                try {
                    $imageFile->move(
                        'back\images',
                        $newFilename
                    );
                } catch (FileException $e) {
                }
                $produit->setImgproduit($newFilename);
            }
            $em->persist($produit);
            $em->flush();
            $mail=[];


            $msg= $produit->getTypeProduit();
            $prix_produit=$produit->getPrixProduit();

            $message = (new \Swift_Message("Vous avez Ajouter un nouveau Produit  ".$msg))

                ->setFrom('ecampix@gmail.com')
                ->setTo('mohamedali.kchaou@esprit.tn')
                //message avec vue twig
                ->setBody(
                    $this->renderView(
                        'email/contact.html.twig'
                    ),
                    'text/html'
                ) ;

            $mailer->send($message);



           return $this->redirectToRoute("afficherproduit");
        }
        return $this->render("produit/ajoutProduit.html.twig",array("form"=>$form->createView()));

    }


    /**
     * @Route("/supprimerproduit/{id}",name="supprimerproduit")
     */
    public function delete($id,EntityManagerInterface $em ,ProduitRepository $repository){
        $cours=$repository->find($id);
        $em->remove($cours);
        $em->flush();

        return $this->redirectToRoute('afficherproduit');
    }



    /**
     * @Route("/{id}/modifierproduit", name="modifierproduit", methods={"GET","POST"})
     */
    public function edit(Request $request, Produit $produit): Response
    {
        $form = $this->createForm(ProduitType::class, $produit);
        $form->add('Confirmer',SubmitType::class);

        $form->handleRequest($request);


        if ($form->isSubmitted() && $form->isValid()) {
            $imageFile = $form->get('imgproduit')->getData();
            if ($imageFile) {
                $originalFilename = pathinfo($imageFile->getClientOriginalName(), PATHINFO_FILENAME);
                $newFilename = $originalFilename.'-'.uniqid().'.'.$imageFile->guessExtension();
                try {
                    $imageFile->move(
                        'back\images',
                        $newFilename
                    );
                } catch (FileException $e) {
                    // ... handle exception if something happens during file upload
                }
                $produit->setImgproduit($newFilename);
            }
            $this->getDoctrine()->getManager()->flush();


            return $this->redirectToRoute('afficherproduit');
        }

        return $this->render('produit/Modifierproduit.html.twig', [
            'produitmodif' => $produit,
            'form' => $form->createView(),
        ]);
    }



    /**
     * @Route("/afficherproduitClient",name="afficherproduitClient")
     */
    public function AfficheProduitClients(EntityManagerInterface $entityManager,Request $request,ProduitRepository $repository,CommandeRepository $commandeRepo){

        $produit = $repository->findOneBy(["id" => $request->get("id")]);

        $commande = new Commande();
       /*  $commande->setProduit($produit);

        $entityManager->persist($commande);
        $entityManager->flush();
*/
        $tableprduits=$repository->listproduitparprix();
        return $this->render('produit/index.html.twig'
            ,['tableproduits'=>$tableprduits]);

    }

    /**
     * @Route("/acheter/{id}",name="acheter")
     */
    public function acheter (EntityManagerInterface $entityManager,ProduitRepository $repository, Request $request,\Swift_Mailer $mailer   )
    {
        $produit = $repository->findOneBy(["id" => $request->get("id")]);

        $commande = new Commande();
        $commande->setProduit($produit);
        $commande->setDate(new \DateTimeImmutable());
        $commande->setPrix($produit->getPrixProduit());
        $entityManager->persist($commande);
        $entityManager->flush();
        $mail=[];


        $msg= $produit->getTypeProduit();
        $prixx=$produit->getPrixProduit();

        $message = (new \Swift_Message("Vous avez passer une commande sur le produit ".$msg."de prix".$prixx))

            ->setFrom('ecampix@gmail.com')
            ->setTo('mohamedali.kchaou@esprit.tn')
            //message avec vue twig
            ->setBody(
                $this->renderView(
                    'email/contact.html.twig'
                ),
                'text/html'
            ) ;

        $mailer->send($message);










        return $this->redirectToRoute('afficherproduitClient');
    }







    /**
     * @Route("/stat", name="stat")
     */
    public function statAction(ProduitRepository $repo)
    {
        $produits= $repo->findAll();
        $produit= [];
        $prixproduit= [];




        foreach($produits as $produit ){
            $produitnom[]=$produit->getTypeProduit();
            $prixproduit[]= $produit->getPrixProduit();
        }

        return $this->render('produit/dashbord.html.twig',
            [
                'produitnom' => json_encode($produitnom),
                'produitprix' => json_encode($prixproduit), 'base2' => 'base2',



            ]);


    }



    /**
     * @Route("/pdf/{id}", name="pdf" ,  methods={"GET"})
     */
    public function pdf($id,ProduitRepository $repository){

        $produit=$repository->find($id);
$pdfOptions = new Options();
$pdfOptions->set('defaultFont', 'Arial');
$dompdf = new Dompdf($pdfOptions);
$html = $this->renderView('produit/pdf.html.twig', [
'pdf' => $produit,

]);
$dompdf->loadHtml($html);

    // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
$dompdf->setPaper('A4', 'portrait');

    // Render the HTML as PDF
$dompdf->render();
    //  $dompdf->stream();
    // Output the generated PDF to Browser (force download)
$dompdf->stream($produit->getTypeProduit(), [
"Attachment" => true
]);

    }

    /**
     * @Route("/produitt/{id}",name="detailproduit")

     */

    public function getById (ProduitRepository $repository, Request $request  )
    {

        $id = $request->get('id');

        $detailproduit = $repository->findOneBy(['id' => $id]);




        return $this->render("produit/detailproduit.html.twig",['detailproduit' => $detailproduit]) ;

    }


}




