<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220305183714 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('DROP INDEX UNIQ_6EEAA67DF347EFB ON commande');
        $this->addSql('ALTER TABLE commande ADD id_user INT NOT NULL, ADD totale INT NOT NULL, ADD numero INT NOT NULL');
        $this->addSql('CREATE UNIQUE INDEX UNIQ_6EEAA67DF347EFB ON commande (produit_id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('DROP INDEX UNIQ_6EEAA67DF347EFB ON commande');
        $this->addSql('ALTER TABLE commande DROP id_user, DROP totale, DROP numero');
        $this->addSql('CREATE INDEX UNIQ_6EEAA67DF347EFB ON commande (produit_id)');
    }
}
