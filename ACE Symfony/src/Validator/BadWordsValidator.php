<?php

namespace App\Validator;

use Symfony\Component\Validator\Constraint;
use Symfony\Component\Validator\ConstraintValidator;

class BadWordsValidator extends ConstraintValidator
{
    private $badWords;


    public function validate($value, Constraint $constraint)
    {
        if (null === $value || '' === $value) {
            return;
        }

        $badWords = $this->getBadWords();

        foreach ($badWords as $badWord) {
            if (stripos($value, $badWord) !== false) {
                $this->context->buildViolation($constraint->message)
                    ->setParameter('{{ word }}', $badWord)
                    ->addViolation();
                break;
            }
        }
    }

    private function getBadWords()
    {
        if (null === $this->badWords) {
            $badWords = [];
            $file = new \SplFileObject(__DIR__ . '/bad_words.txt');

            while (!$file->eof()) {
                $badWord = trim($file->fgets());
                if (!empty($badWord)) {
                    $badWords[] = $badWord;
                }
            }

            $this->badWords = $badWords;
        }

        return $this->badWords;
    }
}
