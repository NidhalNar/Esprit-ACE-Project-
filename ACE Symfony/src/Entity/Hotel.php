<?php

namespace App\Entity;

use App\Repository\HotelRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use App\Entity\Destination;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;

#[ORM\Entity(repositoryClass: HotelRepository::class)]
#[UniqueEntity(fields: ['nom','type','place'], message: 'This Hotel already exists.')]
class Hotel
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(length: 255)]
    #[Assert\NotBlank(message: "Please enter the name.")]
    #[Assert\Regex(
        pattern: '/^[a-zA-Z][a-zA-Z0-9\s]*$/',
        message: "hotel name should start with a character."
    )]
    private ?string $nom = null;

    #[ORM\Column(length: 255)]
   // #[Assert\NotBlank(message: "Please enter the star rating.")]
    //#[Assert\Choice(choices: ["*", "**", "***", "****", "*****"], message: "Invalid star rating.")]
    private ?string $etoile = null;

    #[ORM\Column(length: 255)]
    #[Assert\NotBlank(message: "Please enter the type.")]
    #[Assert\Regex(
        pattern: '/^[a-zA-Z][a-zA-Z0-9\s]*$/',
        message: "type name should start with a character."
    )]
    private ?string $type = null;

    #[ORM\ManyToOne(inversedBy: 'hotels')]
    #[Assert\NotBlank(message: "Please select the destination.")]
    private ?Destination $place = null;

   

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getEtoile(): ?string
    {
        return $this->etoile;
    }

    public function setEtoile(string $etoile): self
    {
        $this->etoile = $etoile;

        return $this;
    }

    public function getType(): ?string
    {
        return $this->type;
    }

    public function setType(string $type): self
    {
        $this->type = $type;

        return $this;
    }

    public function getPlace(): ?Destination
    {
        return $this->place;
    }

    public function setPlace(?Destination $place): self
    {
        $this->place = $place;

        return $this;
    }

 


   
}
