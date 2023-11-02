<?php

namespace App\Entity;

use App\Repository\ReservationRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: ReservationRepository::class)]
class Reservation
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column(type: 'integer')]
    private ?int $id = null;

    #[ORM\Column(name: 'id_user', type: 'integer')]
    private ?int $idUser = null;

    #[ORM\ManyToOne(targetEntity: Evenement::class, inversedBy: 'reservations')]
    #[ORM\JoinColumn(name: 'id_event', referencedColumnName: 'id_event')]
    private ?Evenement $event = null;

    #[ORM\Column(type: 'integer')]
    private ?int $qte = null;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getIdUser(): ?int
    {
        return $this->idUser;
    }

    public function setIdUser(int $idUser): self
    {
        $this->idUser = $idUser;

        return $this;
    }

    public function getEvent(): ?Evenement
    {
        return $this->event;
    }

    public function setEvent(Evenement $event): self
    {
        $this->event = $event;

        return $this;
    }

    public function getQte(): ?int
    {
        return $this->qte;
    }

    public function setQte(int $qte): self
    {
        $this->qte = $qte;

        return $this;
    }
}
