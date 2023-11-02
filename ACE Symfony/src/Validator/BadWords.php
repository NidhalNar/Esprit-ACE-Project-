<?php

namespace App\Validator;

use Symfony\Component\Validator\Constraint;

/**
 * @Annotation
 *
 * @Target({"PROPERTY", "METHOD", "ANNOTATION"})
 */
#[\Attribute(\Attribute::TARGET_PROPERTY | \Attribute::TARGET_METHOD | \Attribute::IS_REPEATABLE)]
class BadWords extends Constraint
{
    public function validatedBy()
    {
        return BadWordsValidator::class;
    }
    public $message = 'The value "{{ value }}" is not valid.';
}
