<?php

namespace App\Entity;

use App\Repository\CategoriesSponsorRepository;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

#[ORM\Entity(repositoryClass: CategoriesSponsorRepository::class)]
class CategoriesSponsor
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]

    private ?int $id_cat = null;

    #[ORM\Column(length: 20)]
    #[Assert\NotBlank(message:"Veuillez remplir ce champ. Il ne peut pas être vide!")]
    #[Assert\Regex(
        pattern:"/^[^0-9]+$/",
        message:"La catégorie ne doit contenir que des lettres, sans chiffres."
    )]
    private ?string $categories = null;
    

    #[ORM\ManyToOne(inversedBy: 'categoriesSponsors')]
    #[ORM\JoinColumn(name: 'id_sponsor', referencedColumnName: 'id_sponsor')]
    private ?Sponsor $sp = null;

    

    public function getId_cat(): ?int
    {
        return $this->id_cat;
    }

    public function getCategories(): ?string
    {
        return $this->categories;
    }

    public function setCategories(string $categories): self
    {
        $this->categories = $categories;

        return $this;
    }

    public function getSp(): ?Sponsor
    {
        return $this->sp;
    }

    public function setSp(?Sponsor $sp): self
    {
        $this->sp = $sp;

        return $this;
    }

  

   
}
