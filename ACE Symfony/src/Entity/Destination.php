<?php

namespace App\Entity;

use App\Repository\DestinationRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;

#[ORM\Entity(repositoryClass: DestinationRepository::class)]
#[UniqueEntity(fields: ['pays', 'ville'], message: 'The combination of "pays" and "ville" already exists.')]
class Destination
{ 
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(length: 25)]
    #[Assert\NotBlank(message: "Please enter the country." )]
    #[Assert\Regex(
        pattern: '/^[a-zA-Z][a-zA-Z0-9\s]*$/',
        message: "plays name should start with a character."
    )]
    private ?string $pays = null;

    #[ORM\Column(length: 25)]
    #[Assert\Regex(
        pattern: '/^[a-zA-Z][a-zA-Z0-9\s]*$/',
        message: "ville name should start with a character."
    )]
    #[Assert\NotBlank(
        message: "Please enter the city."
    )]
    private ?string $ville = null;

    #[ORM\Column(nullable: true)]
    private ?int $id_weather = null;

    #[ORM\OneToMany(mappedBy: 'place', targetEntity: Hotel::class)]
    private Collection $hotels;
    #[ORM\OneToMany(mappedBy: 'destin', targetEntity: Evenement::class)]
    private Collection $evenements;

    
    public function __construct()
    {
        $this->evenements = new ArrayCollection();
        $this->hotels = new ArrayCollection();
    }

  
   

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getPays(): ?string
    {
        return $this->pays;
    }

    public function setPays(string $pays): self
    {
        $this->pays = $pays;

        return $this;
    }

    public function getVille(): ?string
    {
        return $this->ville;
    }

    public function setVille(string $ville): self
    {
        $this->ville = $ville;

        return $this;
    }

    public function getIdWeather(): ?int
    {
        return $this->id_weather;
    }

    public function setIdWeather(?int $id_weather): self
    {
        $this->id_weather = $id_weather;

        return $this;
    }

    /**
     * @return Collection<int, Hotel>
     */
    public function getHotels(): Collection
    {
        return $this->hotels;
    }

    public function addHotel(Hotel $hotel): self
    {
        if (!$this->hotels->contains($hotel)) {
            $this->hotels->add($hotel);
            $hotel->setPlace($this);
        }

        return $this;
    }

    public function removeHotel(Hotel $hotel): self
    {
        if ($this->hotels->removeElement($hotel)) {
            // set the owning side to null (unless already changed)
            if ($hotel->getPlace() === $this) {
                $hotel->setPlace(null);
            }
        }

        return $this;
    }

   
   /**
     * @return Collection<int, Evenement>
     */
    public function getEvenements(): Collection
    {
        return $this->evenements;
    }

    public function addEvenement(Evenement $evenement): self
    {
        if (!$this->evenements->contains($evenement)) {
            $this->evenements->add($evenement);
            $evenement->setDestin($this);
        }

        return $this;
    }

    public function removeEvenement(Evenement $evenement): self
    {
        if ($this->evenements->removeElement($evenement)) {
            // set the owning side to null (unless already changed)
            if ($evenement->getDestin() === $this) {
                $evenement->setDestin(null);
            }
        }

        return $this;
    }

    public function __toString(): string
    {
        return $this->pays ?? '';
        $this->ville ?? '';
    }
}
