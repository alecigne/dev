;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.5.7 Fixed-Size Data / Adding Structure / The Universe of Data
;; ex. 76

#lang htdp/bsl

;; * Movie

(define-struct movie [title producer year])
;; A Movie is a structure:
;; (make-movie String String Integer)
;; Interpretation: a movie, with its title, producer and year.

;; * Person

(define-struct person [name hair eyes phone])
;; A Person is a structure:
;; (make-person String String String String)
;; Interpretation: a person, with its name, hair color, eyes color and
;; phone number.

;; * Pet

;; A PetNumber is an Integer such that:
;; 0 < p <= 100
;; Interpretation: I don't know what a pet number could be...

(define-struct pet [name number])
;; A Pet is a structure:
;; (make-pet String PetNumber)
;; Interpretation: a pet, with its name and pet number (???).

;; * CD

(define-struct CD [artist title price])
;; A CD is a structure:
;; (make-CD String String NonNegativeNumber)
;; Interpretation: a CD, with its artist, title and price.

;; * Sweater

;; A ClothingSize is one of:
;; - "S"
;; - "M"
;; - "L"
;; Interpretation: the size of a sweater.

(define-struct sweater [material size producer])
;; A Sweater is a structure:
;; (make-sweater String ClothingSize String)
;; Interpretation: a sweater, with its material, size and producer.
