<?php

namespace App\Entity;

use App\Repository\PubliciteRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

#[ORM\Entity(repositoryClass: PubliciteRepository::class)]
class Publicite
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id_pub = null;

    #[ORM\Column(length: 25)]
    #[Assert\NotBlank(message:"veuillez remplir ce champ du type")]
    #[Assert\Regex(
        pattern:"/^[^0-9]+$/",
        message:"Le type ne doit pas contenir de chiffres"
    )]
    private ?string $type = null;

    #[ORM\ManyToOne(inversedBy: 'publicites')]
    #[ORM\JoinColumn(name: 'id_event', referencedColumnName: 'id_event')]
    #[Assert\NotBlank(message:"veuillez remplir ce champ SVP")]
    private ?Evenement $evenements = null;

    public function getId(): ?int
    {
        return $this->id_pub;
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

    public function getEvenements(): ?Evenement
    {
        return $this->evenements;
    }

    public function setEvenements(?Evenement $evenements): self
    {
        $this->evenements = $evenements;

        return $this;
    }

    

   
}
