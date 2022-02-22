<?php

namespace App\Entity;

use App\Repository\CommandeRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=CommandeRepository::class)
 */
class Commande
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\OneToOne(targetEntity=Produit::class)
     * @ORM\JoinColumn(nullable=false)
     */
    private $produit;

    /**
     * @ORM\Column(type="integer")
     */
    private $prix;

    /**
     * @ORM\Column(type="date")
     */
    private $date;

    /**
     * @ORM\Column(type="boolean")
     */
    private $valide;


   // private $Commande;

    public function getId(): ?int
    {
        return $this->id;
    }

   /* public function getCommande(): ?Produit
    {
        return $this->Commande;
    }

    public function setCommande(?Produit $Commande): self
    {
        $this->Commande = $Commande;

        return $this;
    }*/

   public function getProduit(): ?Produit
   {
       return $this->produit;
   }

   public function setProduit(?Produit $produit): self
   {
       $this->produit = $produit;

       return $this;
   }

   public function getPrix(): ?int
   {
       return $this->prix;
   }

   public function setPrix(int $prix): self
   {
       $this->prix = $prix;

       return $this;
   }

   public function getDate(): ?\DateTimeInterface
   {
       return $this->date;
   }

   public function setDate(\DateTimeInterface $date): self
   {
       $this->date = $date;

       return $this;
   }

   public function getValide(): ?bool
   {
       return $this->valide;
   }

   public function setValide(bool $valide): self
   {
       $this->valide = $valide;

       return $this;
   }



}
