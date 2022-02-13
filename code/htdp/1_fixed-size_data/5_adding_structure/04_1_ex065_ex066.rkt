;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.5.4 Fixed-Size Data / Adding Structure / Defining Structure Types
;; ex. 65 & 66

#lang htdp/bsl

;; * Movie

(define-struct movie [title producer year])

;; Constructor
(define MOVIE (make-movie "Jurassic Park" "Kathleen Kennedy" 1993))

;; Selectors
(movie-title MOVIE)
(movie-producer MOVIE)
(movie-year MOVIE)

;; Predicate
(movie? MOVIE)

;; * Person

(define-struct person [name hair eyes phone])

;; Constructor
(define PERSON (make-person "Dennis Nedry" "Black" "Brown" "202 555 0131"))

;; Selectors
(person-name PERSON)
(person-hair PERSON)
(person-eyes PERSON)
(person-phone PERSON)

;; Predicate
(person? PERSON)

;; * Pet

(define-struct pet [name number])

;; Constructor
(define PET (make-pet "T-Rex" 42))

;; Selectors
(pet-name PET)
(pet-number PET)

;; Predicate
(pet? PET)

;; * CD

(define-struct CD [artist title price])

;; Constructor
(define A_CD (make-CD "Boards of Canada" "Geogaddi" 20))

;; Selectors
(CD-artist A_CD)
(CD-title A_CD)
(CD-price A_CD)

;; Predicate
(CD? A_CD)

;; * Sweater

(define-struct sweater [material size producer])

;; Constructor
(define SWEATER (make-sweater "Wool" "M" "Cool Shirts Inc."))

;; Selectors
(sweater-material SWEATER)
(sweater-size SWEATER)
(sweater-producer SWEATER)

;; Predicate
(sweater? SWEATER)
