<?php

namespace App\Form;

use App\Entity\Sponsor;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class SponsorType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('intitule')
            ->add('duree_contrat', null, [
                'label' => ' Durée du contrat (en moins)',
            ])
        
           
            ->add('date_debc', null, [
                'label' => 'Date de début du contrat',
            ])
          
            ->add('date_finc', null, [
                'label' => ' Date de fin du contrat ',
            ])
        ;

        

    }


    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Sponsor::class,
        ]);
    }
}
