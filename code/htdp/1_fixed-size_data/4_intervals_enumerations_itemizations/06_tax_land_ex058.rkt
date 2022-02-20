;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.4.6 Fixed-Size Data / Intervals, Enumerations, and Itemizations / Designing with Itemizations
;; Tax Land - ex. 58

;; Introduce constant definitions that separate the intervals for low
;; prices and luxury prices from the others so that the legislators in
;; Tax Land can easily raise the taxes even more.

#lang htdp/bsl

;; * Constants

(define TAX0 0.00)
(define LIMIT1 1000)
(define TAX1 0.05)
(define LIMIT2 10000)
(define TAX2 0.08)

;; * Data definitions

;; A Price is a Number falling into one of these 3 intervals:
;; - 0 to 999
;; - 1 000 to 9 999
;; - 10 000 and more
;; Interpretation: the price of a taxable item.

;; * Functions

;; apply-tax: Price -> Number
;; Apply the appropriate rate.
(define (apply-rate price)
  (cond
    [(and (<= 0 price) (< price 1000))
     (* TAX0 price)]
    [(and (<= 1000 price) (< price 10000))
     (* TAX1 price)]
    [(>= price 10000)
     (* TAX2 price)]))

;; * Tests

(check-expect (apply-rate 0) 0)
(check-expect (apply-rate 537) 0)
(check-expect (apply-rate 1000) 50)
(check-expect (apply-rate 5200) 260)
(check-expect (apply-rate 10000) 800)
(check-expect (apply-rate 12017) 961.36)
