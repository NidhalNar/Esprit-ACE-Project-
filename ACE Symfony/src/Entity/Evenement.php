<?php

namespace App\Entity;

use App\Repository\EvenementRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;





use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: EvenementRepository::class)]
/**
     * @UniqueEntity(fields={"nom_event"}, message="Ce nom d'événement est déjà utilisé.")
     */
class Evenement
{

    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id_event = null;

    #[ORM\Column]
    
    #[Assert\NotBlank(message:"veuillez remplir ce champ de la duree")]
    #[Assert\Regex(
        pattern: "/^[0-9]+$/",
        message: "This value is not valid."
    )]
    private ?float $duree = null;

    #[ORM\Column]
    #[Assert\NotBlank(message:"veuillez remplir ce champ du prix")]
    #[Assert\Regex(
        pattern: "/^[0-9]+$/",
        message: "This value is not valid."
    )]
    private ?float $prix = null;

    #[ORM\Column(type: Types::DATE_MUTABLE)]
    #[Assert\NotBlank(message:"veuillez remplir ce champ de la date de debut")]

    private ?\DateTimeInterface $date_deb = null;

    #[ORM\Column(type: Types::DATE_MUTABLE)]
#[Assert\NotBlank(message: "Veuillez remplir ce champ de la date de fin")]
#[Assert\NotNull(message: "Veuillez remplir ce champ de la date de début")]
#[Assert\GreaterThan(propertyPath: "date_deb", message: "La date de fin doit être après la date de début")]
private ?\DateTimeInterface $date_fin = null;
    

    #[ORM\Column(length: 25)]
    #[Assert\NotBlank(message:"veuillez remplir ce champ du nom de levent")]

    #[Assert\Regex(
        pattern:"/^[^0-9]+$/",
        message:"Le nom ne doit pas contenir de chiffres"
    )]

    private ?string $nom_event = null;
   

   

    #[ORM\OneToMany(mappedBy: 'evenements', targetEntity: Publicite::class)]
    
    private Collection $publicites;

    #[ORM\ManyToOne(inversedBy: 'evenements')]
    #[Assert\NotBlank(message:"veuillez remplir ce champs SVP")]

    private ?Destination $destin = null;

    public function __construct()
    {
        $this->publicites = new ArrayCollection();
    }

   

    public function getId(): ?int
    {
        return $this->id_event;
    }

    public function getDuree(): ?float
    {
        return $this->duree;
    }

    public function setDuree(float $duree): self
    {
        if ($duree <= 0) {
            throw new \InvalidArgumentException('La durée doit être supérieure à 0');
        }
        $this->duree = $duree;
        return $this;
    }

    public function getPrix(): ?float
    {
        return $this->prix;
    }

    public function setPrix(float $prix): self
    {
        if ($prix < 0) {
            throw new \InvalidArgumentException('Le prix doit être un nombre positif');
        }
        $this->prix = $prix;
        return $this;
    }

    public function getDateDeb(): ?\DateTimeInterface
    {
        return $this->date_deb;
    }

    public function setDateDeb(\DateTimeInterface $date_deb): self
    {
        if (empty($date_deb)) {
            throw new \InvalidArgumentException('La date de début ne peut pas être vide');
        }
        $this->date_deb = $date_deb;
        return $this;
    }

    public function getDateFin(): ?\DateTimeInterface
    {
        return $this->date_fin;
    }

    public function setDateFin(\DateTimeInterface $date_fin): self
    {
        if (empty($date_fin)) {
            throw new \InvalidArgumentException('La date de fin ne peut pas être vide');
        }
        $this->date_fin = $date_fin;
        return $this;
    }

    public function getNomEvent(): ?string
    {
        return $this->nom_event;
    }

    public function setNomEvent(string $nom_event): self
    {
        if (empty(trim($nom_event))) {
            throw new \InvalidArgumentException('Le nom de l\'événement ne peut pas être vide');
        }
        $this->nom_event = $nom_event;
        
        return $this;
    }

  

    /**
     * @return Collection<int, Publicite>
     */
    public function getPublicites(): Collection
    {
        return $this->publicites;
    }

    public function addPublicite(Publicite $publicite): self
    {
        if (!$this->publicites->contains($publicite)) {
            $this->publicites->add($publicite);
            $publicite->setEvenements($this);
        }

        return $this;
    }

    public function removePublicite(Publicite $publicite): self
    {
        if ($this->publicites->removeElement($publicite)) {
            // set the owning side to null (unless already changed)
            if ($publicite->getEvenements() === $this) {
                $publicite->setEvenements(null);
            }
        }

        return $this;
    }

    public function __toString(): string
    {
        return $this->nom_event ?? '';
    }

    public function getDestin(): ?Destination
    {
        return $this->destin;
    }

    public function setDestin(?Destination $destin): self
    {
        

        if (empty($destin)) {
            throw new \InvalidArgumentException('La destination ne peut pas être vide');
        }
        $this->destin = $destin;
        return $this;
    }

  
   

   
   

   
}
