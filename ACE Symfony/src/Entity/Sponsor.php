<?php

namespace App\Entity;

use App\Repository\SponsorRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;


use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;

use Doctrine\ORM\EntityManagerInterface;

use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;

use Symfony\Component\Serializer\Annotation\Groups;
 
/**
 * @ORM\Entity
 * @UniqueEntity(fields={"intitule"}, message="Un sponsor avec cet intitulé existe déjà.")
 */
    

#[ORM\Entity(repositoryClass: SponsorRepository::class)]
class Sponsor
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column(name: "id_sponsor", type: "integer")]
   
    private ?int $id_sponsor = null;

    #[ORM\Column(length: 20)]
    #[Assert\NotBlank(message:"Veuillez remplir ce champ. Il ne peut pas être vide!")]
    #[Assert\Regex(
        pattern:"/^[^0-9]+$/",
        message:"L'intitulé ne doit contenir que des lettres, sans chiffres."
    )]
    #[Groups("spon")]
    private ?string $intitule = null;

    #[ORM\Column]
    #[Assert\Positive(message: "La durée du contrat doit être un entier positif.")]
    #[Groups("spon")]

    private ?int $duree_contrat = null;

    #[ORM\Column(type: Types::DATE_MUTABLE)]
    #[Groups("spon")]

    private ?\DateTimeInterface $date_debc = null;

    #[ORM\Column(type: Types::DATE_MUTABLE)]
    #[Assert\NotBlank(message: "Veuillez remplir ce champ de la date de fin")]
    #[Assert\NotNull(message: "Veuillez remplir ce champ de la date de début")]
    #[Assert\GreaterThan(propertyPath: "date_debc", message: "La date de fin doit être après la date de début")] 
    #[Groups("spon")]
   
    private ?\DateTimeInterface $date_finc = null;





    #[ORM\OneToMany(mappedBy: 'sp', targetEntity: CategoriesSponsor::class)]
    private Collection $categoriesSponsors;

    public function __construct()
    {
        $this->categoriesSponsors = new ArrayCollection();
    }

   
  
    


    public function getId_sponsor(): ?int
    {
        return $this->id_sponsor;
    }

    public function getIntitule(): ?string
    {
        return $this->intitule;
    }

    public function setIntitule(string $intitule): self
{
   
    
    $this->intitule = $intitule;
    return $this;
}

    public function getDureeContrat(): ?int
    {
        return $this->duree_contrat;
    }

    public function setDureeContrat(int $duree_contrat): self
    {
        $this->duree_contrat = $duree_contrat;

        return $this;
    }

    public function getDateDebc(): ?\DateTimeInterface
    {
        return $this->date_debc;
    }

    public function setDateDebc(\DateTimeInterface $date_debc): self
    {
        $this->date_debc = $date_debc;

        return $this;
    }

    public function getDateFinc(): ?\DateTimeInterface
    {
        return $this->date_finc;
    }

    public function setDateFinc(\DateTimeInterface $date_finc): self
    {
        $this->date_finc = $date_finc;

        return $this;
    }

    /**
     * @return Collection<int, CategoriesSponsor>
     */
    public function getCategoriesSponsors(): Collection
    {
        return $this->categoriesSponsors;
    }

    public function addCategoriesSponsor(CategoriesSponsor $categoriesSponsor): self
    {
        if (!$this->categoriesSponsors->contains($categoriesSponsor)) {
            $this->categoriesSponsors->add($categoriesSponsor);
            $categoriesSponsor->setSp($this);
        }

        return $this;
    }

    public function removeCategoriesSponsor(CategoriesSponsor $categoriesSponsor): self
    {
        if ($this->categoriesSponsors->removeElement($categoriesSponsor)) {
            // set the owning side to null (unless already changed)
            if ($categoriesSponsor->getSp() === $this) {
                $categoriesSponsor->setSp(null);
            }
        }

        return $this;
    }

    public function __toString(): string
    {
        return $this->getIntitule() ?? '';
    }
   
}
