;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.3.6 Fixed-Size Data / How to Design Programs / Designing World Programs
;; Exercise 39

#lang racket                            ; images don't appear within Emacs with
                                        ; htdp/bsl

(require 2htdp/image)

; Physical constants

(define WHEEL-RADIUS 10)
(define WHEEL-DISTANCE (* WHEEL-RADIUS 3))
(define WHEEL-COLOR "black")

(define CAR-LENGTH (* WHEEL-RADIUS 8))
(define CAR-HEIGHT (/ CAR-LENGTH 4))
(define CAR-COLOR "red")

;; Graphical constants

(define WHEEL
  (circle WHEEL-RADIUS "solid" WHEEL-COLOR))

(define SPACE
  (rectangle WHEEL-DISTANCE 0 "solid" "white"))

(define BOTH-WHEELS
  (beside WHEEL SPACE WHEEL))

(define CAR-BODY
  (rectangle CAR-LENGTH CAR-HEIGHT "solid" CAR-COLOR))

(define CAR
  (underlay/align/offset "middle" "bottom"
                         CAR-BODY
                         0 (/ CAR-HEIGHT 2)
                         BOTH-WHEELS))

CAR
