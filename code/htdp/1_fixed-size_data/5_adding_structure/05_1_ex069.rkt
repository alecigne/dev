;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.5.5 Fixed-Size Data / Adding Structure / Computing with Structures
;; ex. 69

#lang htdp/bsl

;; * Movie

(define-struct movie [title producer year])
(define MOVIE (make-movie "Jurassic Park" "Kathleen Kennedy" 1993))

;; +--------+--------+------+
;; | title  |producer| year |
;; +--------+--------+------+
;; |Jurassic|Kathleen| 1993 |
;; |Park    |Kennedy |      |
;; +--------+--------+------+

;; * Person

(define-struct person [name hair eyes phone])
(define PERSON (make-person "Dennis Nedry" "Black" "Brown" "202 555 0131"))

;; +------+------+------+---------+
;; | name | hair | eyes |  phone  |
;; +------+------+------+---------+
;; |Dennis|Black |Brown | 202 555 |
;; |Nedry |      |      |  0131   |
;; +------+------+------+---------+

;; * Pet

(define-struct pet [name number])
(define PET (make-pet "T-Rex" 42))

;; +-------+-------+
;; | name  |number |
;; +-------+-------+
;; | T-Rex |  42   |
;; +-------+-------+

;; * CD

(define-struct CD [artist title price])
(define A_CD (make-CD "Boards of Canada" "Geogaddi" 20))

;; +---------+--------+-----+
;; |artist   |title   |price|
;; +---------+--------+-----+
;; |Boards of|Geogaddi|20   |
;; |Canada   |        |     |
;; +---------+--------+-----+

;; * Sweater

(define-struct sweater [material size producer])
(define SWEATER (make-sweater "Wool" "M" "Cool Shirts Inc."))

;; +--------+-----+-----------+
;; |material|size |producer   |
;; +--------+-----+-----------+
;; |Wool    |M    |Cool Shirts|
;; |        |     |Inc.       |
;; +--------+-----+-----------+
