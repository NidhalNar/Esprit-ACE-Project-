<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20230428211252 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE categories_sponsor (id_cat INT AUTO_INCREMENT NOT NULL, id_sponsor INT DEFAULT NULL, categories VARCHAR(20) NOT NULL, INDEX IDX_A98618745F1160A4 (id_sponsor), PRIMARY KEY(id_cat)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE destination (id INT AUTO_INCREMENT NOT NULL, pays VARCHAR(25) NOT NULL, ville VARCHAR(25) NOT NULL, id_weather INT DEFAULT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE evenement (id_event INT AUTO_INCREMENT NOT NULL, destin_id INT DEFAULT NULL, duree DOUBLE PRECISION NOT NULL, prix DOUBLE PRECISION NOT NULL, date_deb DATE NOT NULL, date_fin DATE NOT NULL, nom_event VARCHAR(25) NOT NULL, INDEX IDX_B26681EE4656696 (destin_id), PRIMARY KEY(id_event)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE hotel (id INT AUTO_INCREMENT NOT NULL, place_id INT DEFAULT NULL, nom VARCHAR(255) NOT NULL, etoile VARCHAR(255) NOT NULL, type VARCHAR(255) NOT NULL, INDEX IDX_3535ED9DA6A219 (place_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE message (id_message INT AUTO_INCREMENT NOT NULL, id_post INT DEFAULT NULL, contenu VARCHAR(1000) NOT NULL, note BIGINT NOT NULL, created_at DATETIME NOT NULL, updated_at DATETIME NOT NULL, INDEX IDX_B6BD307FD1AA708F (id_post), PRIMARY KEY(id_message)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE post (id_post INT AUTO_INCREMENT NOT NULL, sujet VARCHAR(256) NOT NULL, PRIMARY KEY(id_post)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE publicite (id_pub INT AUTO_INCREMENT NOT NULL, id_event INT DEFAULT NULL, type VARCHAR(25) NOT NULL, INDEX IDX_1D394E39D52B4B97 (id_event), PRIMARY KEY(id_pub)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE reservation (id INT AUTO_INCREMENT NOT NULL, id_res INT NOT NULL, id_user INT NOT NULL, id_event INT NOT NULL, qte INT NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE reset_password_request (id INT AUTO_INCREMENT NOT NULL, user_id INT NOT NULL, selector VARCHAR(20) NOT NULL, hashed_token VARCHAR(100) NOT NULL, requested_at DATETIME NOT NULL COMMENT \'(DC2Type:datetime_immutable)\', expires_at DATETIME NOT NULL COMMENT \'(DC2Type:datetime_immutable)\', INDEX IDX_7CE748AA76ED395 (user_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE sponsor (id_sponsor INT AUTO_INCREMENT NOT NULL, intitule VARCHAR(20) NOT NULL, duree_contrat INT NOT NULL, date_debc DATE NOT NULL, date_finc DATE NOT NULL, PRIMARY KEY(id_sponsor)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE user (id INT AUTO_INCREMENT NOT NULL, email VARCHAR(180) NOT NULL, roles LONGTEXT NOT NULL COMMENT \'(DC2Type:json)\', password VARCHAR(255) NOT NULL, address VARCHAR(255) NOT NULL, telephone VARCHAR(255) DEFAULT NULL, login VARCHAR(255) NOT NULL, nom VARCHAR(255) NOT NULL, is_verified TINYINT(1) NOT NULL, UNIQUE INDEX UNIQ_8D93D649E7927C74 (email), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE messenger_messages (id BIGINT AUTO_INCREMENT NOT NULL, body LONGTEXT NOT NULL, headers LONGTEXT NOT NULL, queue_name VARCHAR(190) NOT NULL, created_at DATETIME NOT NULL, available_at DATETIME NOT NULL, delivered_at DATETIME DEFAULT NULL, INDEX IDX_75EA56E0FB7336F0 (queue_name), INDEX IDX_75EA56E0E3BD61CE (available_at), INDEX IDX_75EA56E016BA31DB (delivered_at), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE categories_sponsor ADD CONSTRAINT FK_A98618745F1160A4 FOREIGN KEY (id_sponsor) REFERENCES sponsor (id_sponsor)');
        $this->addSql('ALTER TABLE evenement ADD CONSTRAINT FK_B26681EE4656696 FOREIGN KEY (destin_id) REFERENCES destination (id)');
        $this->addSql('ALTER TABLE hotel ADD CONSTRAINT FK_3535ED9DA6A219 FOREIGN KEY (place_id) REFERENCES destination (id)');
        $this->addSql('ALTER TABLE message ADD CONSTRAINT FK_B6BD307FD1AA708F FOREIGN KEY (id_post) REFERENCES post (id_post)');
        $this->addSql('ALTER TABLE publicite ADD CONSTRAINT FK_1D394E39D52B4B97 FOREIGN KEY (id_event) REFERENCES evenement (id_event)');
        $this->addSql('ALTER TABLE reset_password_request ADD CONSTRAINT FK_7CE748AA76ED395 FOREIGN KEY (user_id) REFERENCES user (id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE categories_sponsor DROP FOREIGN KEY FK_A98618745F1160A4');
        $this->addSql('ALTER TABLE evenement DROP FOREIGN KEY FK_B26681EE4656696');
        $this->addSql('ALTER TABLE hotel DROP FOREIGN KEY FK_3535ED9DA6A219');
        $this->addSql('ALTER TABLE message DROP FOREIGN KEY FK_B6BD307FD1AA708F');
        $this->addSql('ALTER TABLE publicite DROP FOREIGN KEY FK_1D394E39D52B4B97');
        $this->addSql('ALTER TABLE reset_password_request DROP FOREIGN KEY FK_7CE748AA76ED395');
        $this->addSql('DROP TABLE categories_sponsor');
        $this->addSql('DROP TABLE destination');
        $this->addSql('DROP TABLE evenement');
        $this->addSql('DROP TABLE hotel');
        $this->addSql('DROP TABLE message');
        $this->addSql('DROP TABLE post');
        $this->addSql('DROP TABLE publicite');
        $this->addSql('DROP TABLE reservation');
        $this->addSql('DROP TABLE reset_password_request');
        $this->addSql('DROP TABLE sponsor');
        $this->addSql('DROP TABLE user');
        $this->addSql('DROP TABLE messenger_messages');
    }
}
