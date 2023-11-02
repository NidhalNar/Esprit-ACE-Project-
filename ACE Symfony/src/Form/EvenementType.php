<?php

namespace App\Form;

use App\Entity\Evenement;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class EvenementType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('duree', null, [
                'label' => 'Durée (en heures)',
            ])
            ->add('prix', null, [
                'label' => 'Prix (en dinars)',
            ])
            ->add('date_deb', null, [
                'label' => 'Date de début',
            ])
            ->add('date_fin', null, [
                'label' => 'Date de fin',
            ])
            ->add('nom_event', null, [
                'label' => 'Nom de l\'événement',
            ])
            ->add('destin', null, [
                'label' => 'Destination',
            ]);
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Evenement::class,
        ]);
    }
}
