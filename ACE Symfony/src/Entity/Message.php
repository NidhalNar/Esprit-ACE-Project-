<?php

namespace App\Entity;

use App\Repository\MessageRepository;
use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Gedmo\Mapping\Annotation as Gedmo;
use App\Validator\BadWords;

#[ORM\Entity(repositoryClass: MessageRepository::class)]
class Message
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id_message = null;

    #[ORM\ManyToOne(inversedBy: 'sp')]
    #[ORM\JoinColumn(name: 'id_post', referencedColumnName: 'id_post')]

    private ?Post $id_post = null;

    #[ORM\Column(length: 1000)]
    #[Assert\NotBlank(message: "le contenu du message ne doit pas être vide!")]
    /**
     * @BadWords(message="The message contains bad words.")
     */
    private ?string $contenu = null;

    #[ORM\Column(type: Types::BIGINT)]
    #[Assert\LessThanOrEqual(['value' => 5, 'message' => 'la valeur maximale autorisée est 5'])]

    private ?string $note = null;

    #[ORM\Column(type: Types::DATETIME_MUTABLE)]
    /**
     * @Gedmo\Timestampable(on="create")
     */
    private ?\DateTimeInterface $createdAt = null;

    #[ORM\Column(type: Types::DATETIME_MUTABLE)]
    /**
     * @Gedmo\Timestampable(on="update")
     */
    private ?\DateTimeInterface $updatedAt = null;

    #[ORM\Column(type: Types::INTEGER, options: ['default' => 0])]
    private ?int $likes = 0;



    public function getId_message(): ?int
    {
        return $this->id_message;
    }

    public function getIdPost(): ?Post
    {
        return $this->id_post;
    }

    public function setIdPost(?Post $id_post): self
    {
        $this->id_post = $id_post;

        return $this;
    }

    public function getContenu(): ?string
    {
        return $this->contenu;
    }

    public function setContenu(string $contenu): self
    {
        $this->contenu = $contenu;

        return $this;
    }

    public function getNote(): ?string
    {
        return $this->note;
    }

    public function setNote(string $note): self
    {
        $this->note = $note;

        return $this;
    }

    public function getCreatedAt(): ?\DateTimeInterface
    {
        return $this->createdAt;
    }



    public function getUpdatedAt(): ?\DateTimeInterface
    {
        return $this->updatedAt;
    }

    public function getLikes(): ?int
    {
        return $this->likes;
    }

    public function setLikes(int $likes): self
    {
        $this->likes = $likes;

        return $this;
    }

    public function incrementLikes(): self
    {
        $this->likes++;

        return $this;
    }

    public function decrementLikes(): self
    {
        $this->likes--;

        return $this;
    }
}
