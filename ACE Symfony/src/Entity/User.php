<?php

namespace App\Entity;

use App\Repository\UserRepository;
use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Symfony\Component\Security\Core\User\PasswordAuthenticatedUserInterface;
use Symfony\Component\Security\Core\User\UserInterface;
use Symfony\Component\Validator\Constraints as Assert;

#[ORM\Entity(repositoryClass: UserRepository::class)]
#[UniqueEntity(fields: ['email'], message: 'Il existe déjà un compte avec cet email')]
class User implements UserInterface, PasswordAuthenticatedUserInterface
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(length: 180, unique: true)]
    #[Assert\Email(message: 'L email "{{ value }}" n est pas un email valide.')]
    #[Assert\NotBlank (message: 'Veuillez saisir votre adresse e-mail.')]
    private ?string $email = null;

    #[ORM\Column]
    private array $roles = [];

    /**
     * @var string The hashed password
     */
    #[ORM\Column]
    private ?string $password = null;

    #[ORM\Column(length: 255)]
    #[Assert\NotBlank (message: 'Veuillez entrer votre adresse.')]
    #[Assert\Regex(pattern: '/^[a-zA-Z\s]+$/i', message: 'Votre adresse ne doit contenir que des lettres')]
    #[Assert\Length(min: 3, max: 255, minMessage: 'Votre adresse doit comporter au moins {{ limit }} caractères')]
    private ?string $address = null;

    #[ORM\Column(length: 255, nullable: true)]
    #[Assert\NotBlank (message: 'Veuillez entrer votre téléphone.')]
    #[Assert\Regex(pattern: '/^[0-9]+$/i', message: 'Votre numéro de téléphone ne doit contenir que des chiffres')]
    #[Assert\Length(min: 8, max: 8, exactMessage: 'Votre numéro de téléphone doit contenir {{ limit }} numéros')]
    private ?string $telephone = null;

    #[ORM\Column(length: 255)]
    #[Assert\NotBlank(message: 'Veuillez saisir votre identifiant.')]
    #[Assert\Regex(pattern: '/^[a-zA-Z0-9_]+$/i', message: 'Votre identifiant ne doit contenir que des lettres, des chiffres et des traits de soulignement')]
    #[Assert\Length(min: 3, max: 255, minMessage: 'Votre identifiant doit comporter au moins {{ limit }} caractères')]
    private ?string $login = null;


    #[ORM\Column(length: 255)]
    #[Assert\NotBlank (message: 'Veuillez entrer votre nom.')]
    #[Assert\Regex(pattern: '/^[a-zA-Z]+$/i', message: 'Votre nom ne doit contenir que des lettres')]
    #[Assert\Length(min: 3, max: 255, minMessage: 'Votre nom doit comporter au moins {{ limit }} caractères')]
    private ?string $nom = null;

    
    #[ORM\Column(type: 'boolean')]
    private $isVerified = false;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): self
    {
        $this->email = $email;

        return $this;
    }

    /**
     * A visual identifier that represents this user.
     *
     * @see UserInterface
     */
    public function getUserIdentifier(): string
    {
        return (string) $this->email;
    }

    /**
     * @deprecated since Symfony 5.3, use getUserIdentifier instead
     */
    public function getUsername(): string
    {
        return (string) $this->email;
    }

    /**
     * @see UserInterface
     */
    public function getRoles(): array
    {
        $roles = $this->roles;
        // guarantee every user at least has ROLE_USER
        // $roles[] = 'ROLE_USER';

        return array_unique($roles);
    }

    public function setRoles(array $roles): self
    {
        $this->roles = $roles;

        return $this;
    }

    /**
     * @see PasswordAuthenticatedUserInterface
     */
    public function getPassword(): string
    {
        return $this->password;
    }

    public function setPassword(string $password): self
    {
        $this->password = $password;

        return $this;
    }

    /**
     * Returning a salt is only needed, if you are not using a modern
     * hashing algorithm (e.g. bcrypt or sodium) in your security.yaml.
     *
     * @see UserInterface
     */
    public function getSalt(): ?string
    {
        return null;
    }

    /**
     * @see UserInterface
     */
    public function eraseCredentials()
    {
        // If you store any temporary, sensitive data on the user, clear it here
        // $this->plainPassword = null;
    }

    public function getAddress(): ?string
    {
        return $this->address;
    }

    public function setAddress(string $address): self
    {
        $this->address = $address;

        return $this;
    }

    public function getTelephone(): ?string
    {
        return $this->telephone;
    }

    public function setTelephone(string $telephone): self
    {
        $this->telephone = $telephone;

        return $this;
    }

    public function getLogin(): ?string
    {
        return $this->login;
    }

    public function setLogin(string $login): self
    {
        $this->login = $login;

        return $this;
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
    public function isVerified(): bool
    {
        return $this->isVerified;
    }

    public function setIsVerified(bool $isVerified): self
    {
        $this->isVerified = $isVerified;

        return $this;
    }
}
