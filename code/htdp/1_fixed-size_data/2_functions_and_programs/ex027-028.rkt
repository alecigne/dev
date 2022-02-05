;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.2.3 Fixed-Size Data / Functions and Programs / Composing Functions
;; Exercises 27 & 28

#lang htdp/bsl

(define BASE-ATTENDANCE 120)
(define BASE-PRICE 5.0)
(define PRICE-STEP 0.1)
(define ATTENDANCE-STEP 15)
(define FIXED-COST 180.00)
(define COST-PER-ATTENDEE 0.04)

(define (attendees ticket-price)
  (- BASE-ATTENDANCE
     (* (- ticket-price BASE-PRICE)
        (/ ATTENDANCE-STEP PRICE-STEP))))

(define (revenue ticket-price)
  (* ticket-price (attendees ticket-price)))

(define (cost ticket-price)
  (+ FIXED-COST (* COST-PER-ATTENDEE (attendees ticket-price))))

(define (profit ticket-price)
  (- (revenue ticket-price)
     (cost ticket-price)))

(profit 1)
(profit 2)
(profit 3)
(profit 4)
(profit 5)
(profit 2.9)                            ; best ticket price
