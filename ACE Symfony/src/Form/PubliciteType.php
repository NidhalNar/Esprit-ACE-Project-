<?php

namespace App\Form;

use App\Entity\Evenement;
use App\Entity\Publicite;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class PubliciteType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('type', null, [
                'label' => 'Type de publicité',
            ])
            ->add('evenements', null, [
                'label' => 'Événements',
                'attr' => [
                    'class' => 'select2'
                ]
            ])
       
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Publicite::class,
        ]);
    }
}
