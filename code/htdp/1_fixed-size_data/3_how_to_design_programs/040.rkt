;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.3.6 Fixed-Size Data / How to Design Programs / Designing World Programs
;; Exercise 40

#lang htdp/bsl

;; tock: CarPosition -> CarPosition
;; Moves the car by 3 pixels for every clock tick.
;; Expectations:
;;   given: 20, expect: 23
;;   given: 78, expect: 81
(define (tock cw)
  (+ cw 3))

(check-expect (tock 20) 23)
(check-expect (tock 78) 81)
