;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.5.8 Fixed-Size Data / Adding Structure / Designing with Structures
;; ex. 80

#lang htdp/bsl

;; * Movie

(define-struct movie [title director year])

;; do-with-movie: Movie -> ?
;; Do something with a movie.
(define (do-with-movie movie)
  (...
   (movie-title movie)
   ...
   (movie-director movie)
   ...
   (movie-year movie)
   ...))

;; * Pet

(define-struct pet [name number])

;; do-with-pet: Pet -> ?
;; Do something with a pet.
(define (do-with-pet pet)
  (...
   (pet-name pet)
   ...
   (pet-number pet)
   ...))

;; * CD

(define-struct CD [artist title price])

;; do-with-CD: CD -> ?
;; Do something with a CD.
(define (do-with-CD CD)
  (...
   (CD-artist CD)
   ...
   (CD-title CD)
   ...
   (CD-price CD)
   ...))

;; * Sweater

(define-struct sweater [material size color])

;; do-with-sweater: sweater -> ?
;; Do something with a sweater.
(define (do-with-sweater sweater)
  (...
   (sweater-material sweater)
   ...
   (sweater-size sweater)
   ...
   (sweater-color sweater)
   ...))
