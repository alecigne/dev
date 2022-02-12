;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.5.7 Fixed-Size Data / Adding Structure / The Universe of Data
;; ex. 79

#lang htdp/bsl

;; * Color

;; A Color is one of:
;; — "white"
;; — "yellow"
;; — "orange"
;; — "green"
;; — "red"
;; — "blue"
;; — "black"

;; Examples
;; "For an enumeration, use several of the items of the enumeration".
(define white "white")
(define orange "orange")

;; * Happiness

;; Happiness is a Number such that:
;; 0 <= h <= 100
;; Interpretation: a level of happiness.

;; Examples
;; For intervals, use the end points (if they are included) and at
;; least one interior point.
(define happiness1 0)
(define happiness2 50)
(define happiness3 100)

;; * Person

(define-struct person [first-name last-name male?])
;; A Person is a structure:
;; (make-person String String Boolean)

;; Examples
;; For data definitions for structures, follow the natural language
;; description, that is, use the constructor and pick an example from
;; the data collection named for each field.
(define person1 (make-person "Jacques" "Chirac" #true))
(define person2 (make-person "Bernadette" "Chirac" #false))

;; Is it a good idea to use a field name that looks like the name of a
;; predicate?

;; Although it allows (person-male? person), I don't really like it. I
;; would prefer a Sex type as an enumeration.

;; * Dog

(define-struct dog [owner name age happiness])
;; A Dog is a structure:
;; (make-dog Person String PositiveInteger Happiness)
;; Interpretation: a dog!!!

;; Examples
(define dog1 (make-dog
              (make-person "Michel" "Legrand" #true)
              "Médor"
              12
              98))

;; * Weapon

;; A Missile is one of:
;; — #false
;; — Posn
;; Interpretation: #false means the missile hasn't been fired yet; a
;; Posn means it is in flight.

;; Examples
(define missile1 #false)
(define missile2 (make-posn 10 10))
