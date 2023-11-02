<?php

namespace App\Entity;

use App\Repository\PostRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

#[ORM\Entity(repositoryClass: PostRepository::class)]
class Post
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id_post = null;

    #[ORM\Column(length: 256)]
    #[Assert\NotBlank(message: "attention!!vide!!")]
    #[Assert\Length(max: 256, maxMessage: "taille limite atteinte")]


    private ?string $sujet = null;

    #[ORM\OneToMany(mappedBy: 'id_post', targetEntity: Message::class, cascade: ['remove'])]
    private Collection $sp;

    #[ORM\Column(type: "boolean", options: ["default" => false])]
    private ?bool $signaled = false;

    #[ORM\Column]
    private ?int $view = 0;

    public function __construct()
    {
        $this->sp = new ArrayCollection();
    }

    public function getId_post(): ?int
    {
        return $this->id_post;
    }

    public function getSujet(): ?string
    {
        return $this->sujet;
    }

    public function setSujet(string $sujet): self
    {
        $this->sujet = $sujet;

        return $this;
    }

    public function __toString(): string
    {
        return $this->getSujet() ?? '';
    }

    /**
     * @return Collection<int, Message>
     */
    public function getSp(): Collection
    {
        return $this->sp;
    }

    public function addSp(Message $sp): self
    {
        if (!$this->sp->contains($sp)) {
            $this->sp->add($sp);
            $sp->setIdPost($this);
        }

        return $this;
    }

    public function removeSp(Message $sp): self
    {
        if ($this->sp->removeElement($sp)) {
            // set the owning side to null (unless already changed)
            if ($sp->getIdPost() === $this) {
                $sp->setIdPost(null);
            }
        }

        return $this;
    }

    public function isSignaled(): ?bool
    {
        return $this->signaled;
    }

    public function setSignaled(bool $signaled): self
    {
        $this->signaled = $signaled;

        return $this;
    }

    public function getView(): ?int
    {
        return $this->view;
    }

    public function setView(int $view): self
    {
        $this->view = $view;

        return $this;
    }

    public function incrementView(): self
    {
        $this->view++;

        return $this;
    }
}
