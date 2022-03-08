<?php

namespace App\Entity;

use App\Repository\CommandeRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
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
     * @ORM\Column(type="integer")
     */
    private $IdUser;

    /**
     * @ORM\Column(type="integer")
     */
    private $totale;

    /**
     * @ORM\Column(type="integer")
     */
    private $numero;

    /**
     * @ORM\Column(type="date")
     */
    private $date;

    /**
     * @ORM\OneToMany(targetEntity=LigneCommande::class, mappedBy="Commande")
     */
    private $ligneCommandes;

    public function __construct()
    {
        $this->ligneCommandes = new ArrayCollection();
    }





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

    public function getNumero(): ?int
    {
        return $this->Numero;
    }

    public function setNumero(int $Numero): self
    {
        $this->Numero = $Numero;

        return $this;
    }


    public function getTotale(): ?int
    {
        return $this->totale;
    }

    public function setTotale(int $totale): self
    {
        $this->totale = $totale;

        return $this;
    }

    public function getIdUser(): ?int
    {
        return $this->IdUser;
    }

    public function setIdUser(int $IdUser): self
    {
        $this->IdUser = $IdUser;

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

   /**
    * @return Collection|LigneCommande[]
    */
   public function getLigneCommandes(): Collection
   {
       return $this->ligneCommandes;
   }

   public function addLigneCommande(LigneCommande $ligneCommande): self
   {
       if (!$this->ligneCommandes->contains($ligneCommande)) {
           $this->ligneCommandes[] = $ligneCommande;
           $ligneCommande->setCommande($this);
       }

       return $this;
   }

   public function removeLigneCommande(LigneCommande $ligneCommande): self
   {
       if ($this->ligneCommandes->removeElement($ligneCommande)) {
           // set the owning side to null (unless already changed)
           if ($ligneCommande->getCommande() === $this) {
               $ligneCommande->setCommande(null);
           }
       }

       return $this;
   }





}
