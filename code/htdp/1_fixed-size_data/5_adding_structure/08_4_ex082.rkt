;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.5.8 Fixed-Size Data / Adding Structure / Designing with Structures
;; ex. 82

#lang htdp/bsl

;; * Data definitions

;; A LCL is one of:
;; - A 1String such as "a" <= x <= "z"
;; - #false
;; Interpretation: a lowercase letter, from A to Z, or no letter.

(define-struct 3letterword [lcl1 lcl2 lcl3])
;; A 3LetterWord is a structure:
;; (make-3letterword LCL LCL LCL)
;; Interpretation: a 3-letter word, consisting of 3 LCL.
;; Examples:
(define 3letterword1 (make-3letterword #false #false #false))
(define 3letterword2 (make-3letterword "a" #false "c"))
(define 3letterword3 (make-3letterword "a" "b" "c"))
(define 3letterword4 (make-3letterword "x" "b" "z"))

;; * Functions

;; compare-words: 3LetterWord 3LetterWord -> 3LetterWord
;; Produce a 3LetterWord from two 3LetterWord. If two positions match,
;; it is kept as-is; otherwise, it becomes #false.
(define (compare-words first-3letterword second-3letterword)
  (make-3letterword
   (compare-letters (3letterword-lcl1 first-3letterword)
                    (3letterword-lcl1 second-3letterword))
   (compare-letters (3letterword-lcl2 first-3letterword)
                    (3letterword-lcl2 second-3letterword))
   (compare-letters (3letterword-lcl3 first-3letterword)
                    (3letterword-lcl3 second-3letterword))))

;; compare-letters: LCL LCL -> LCL1
;; If the two LCL agree, produce the LCL. Otherwise, produce #false.
(define (compare-letters lcl1 lcl2)
  (if (equal? lcl1 lcl2) lcl1 #false))

;; * Tests

;; compare-words

(check-expect
 (compare-words 3letterword1 3letterword1)
 3letterword1)

(check-expect
 (compare-words 3letterword1 3letterword2)
 (make-3letterword #false #false #false))

(check-expect
 (compare-words 3letterword2 3letterword2)
 3letterword2)

(check-expect
 (compare-words 3letterword3 3letterword3)
 3letterword3)

(check-expect
 (compare-words 3letterword3 3letterword4)
 (make-3letterword #false "b" #false))

;; compare-letters

(check-expect
 (compare-letters #false #false)
 #false)

(check-expect
 (compare-letters "a" "a")
 "a")

(check-expect
 (compare-letters "a" #false)
 #false)

(check-expect
 (compare-letters "a" "b")
 #false)
