<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220305185701 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE commande DROP INDEX IDX_6EEAA67DF347EFB, ADD UNIQUE INDEX UNIQ_6EEAA67DF347EFB (produit_id)');
        $this->addSql('ALTER TABLE commande CHANGE produit_id produit_id INT NOT NULL, CHANGE prix prix INT NOT NULL, CHANGE numero numero INT NOT NULL');
        $this->addSql('ALTER TABLE produit CHANGE prixproduit prix_produit VARCHAR(255) NOT NULL');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE commande DROP INDEX UNIQ_6EEAA67DF347EFB, ADD INDEX IDX_6EEAA67DF347EFB (produit_id)');
        $this->addSql('ALTER TABLE commande CHANGE produit_id produit_id INT DEFAULT NULL, CHANGE prix prix INT DEFAULT NULL, CHANGE numero numero INT DEFAULT NULL');
        $this->addSql('ALTER TABLE produit CHANGE prix_produit prixProduit VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`');
    }
}
