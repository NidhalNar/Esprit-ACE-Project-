<?php

namespace App\Repository;

use App\Entity\CategoriesSponsor;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @extends ServiceEntityRepository<CategoriesSponsor>
 *
 * @method CategoriesSponsor|null find($id, $lockMode = null, $lockVersion = null)
 * @method CategoriesSponsor|null findOneBy(array $criteria, array $orderBy = null)
 * @method CategoriesSponsor[]    findAll()
 * @method CategoriesSponsor[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class CategoriesSponsorRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, CategoriesSponsor::class);
    }

    public function save(CategoriesSponsor $entity, bool $flush = false): void
    {
        $this->getEntityManager()->persist($entity);

        if ($flush) {
            $this->getEntityManager()->flush();
        }
    }

    public function remove(CategoriesSponsor $entity, bool $flush = false): void
    {
        $this->getEntityManager()->remove($entity);

        if ($flush) {
            $this->getEntityManager()->flush();
        }
    }

//    /**
//     * @return CategoriesSponsor[] Returns an array of CategoriesSponsor objects
//     */
//    public function findByExampleField($value): array
//    {
//        return $this->createQueryBuilder('c')
//            ->andWhere('c.exampleField = :val')
//            ->setParameter('val', $value)
//            ->orderBy('c.id', 'ASC')
//            ->setMaxResults(10)
//            ->getQuery()
//            ->getResult()
//        ;
//    }

//    public function findOneBySomeField($value): ?CategoriesSponsor
//    {
//        return $this->createQueryBuilder('c')
//            ->andWhere('c.exampleField = :val')
//            ->setParameter('val', $value)
//            ->getQuery()
//            ->getOneOrNullResult()
//        ;
//    }
}
