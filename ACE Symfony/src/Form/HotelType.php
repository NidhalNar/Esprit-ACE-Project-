<?php

namespace App\Form;

use App\Entity\Hotel;
use App\Entity\Destination;
use Symfony\Component\Form\AbstractType;
use App\Repository\DestinationRepository;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\RangeType;
use App\Repository\HotelRepository;





class HotelType extends AbstractType


{
  
    
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nom', null, [
                'label_attr' => [
                    'style' => 'font-weight: bold;'
                ]
            ])

            
            ->add('etoile', RangeType::class, [
                'attr' => [
                    'min' => 0,
                    'max' => 5,
                    'step' => 1,
                    'value' => 0, // Set initial value
                    'class' => 'slider-hover', // Add a CSS class to the slider element
                ],
                'label_attr' => [
                    'style' => 'font-weight: bold;'
                ],
                'label' => 'Choose a review',
                'data' => 0, // Set the initial value using the data option
            ])



            ->add('type', null, [
                'label_attr' => [
                    'style' => 'font-weight: bold;'
                ]
            ])

            
            ->add('place', EntityType::class, [
                'class' => Destination::class,
                'query_builder' => function (DestinationRepository $repository) {
                    return $repository->createQueryBuilder('d')
                        ->select('DISTINCT  d')
                        ->groupBy('d.pays');
                },
                'choice_label' => 'pays',
                'expanded' => false,
                'multiple' => false,
                'placeholder' => 'Choose a destination',
                'label_attr' => [
                    'style' => 'font-weight: bold;'
                ],
            ]);
    }
    

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Hotel::class,
        ]);
    }
}
