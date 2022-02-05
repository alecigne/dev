;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.2.4 Fixed-Size Data / Functions and Programs / Global Constants
;; Exercise 30

#lang htdp/bsl

(define BASE-ATTENDANCE 120)
(define BASE-PRICE 5.0)
(define PRICE-STEP 0.1)
(define ATTENDANCE-STEP 15)
(define COST-PER-ATTENDEE 1.50)
(define PRICE-SENSITIVITY (/ ATTENDANCE-STEP PRICE-STEP))

(define (attendees ticket-price)
  (- BASE-ATTENDANCE
     (* (- ticket-price BASE-PRICE)
        PRICE-SENSITIVITY)))

(define (revenue ticket-price)
  (* ticket-price (attendees ticket-price)))

(define (cost ticket-price)
  (* COST-PER-ATTENDEE (attendees ticket-price)))

(define (profit ticket-price)
  (- (revenue ticket-price)
     (cost ticket-price)))

(profit 3)
(profit 4)
(profit 5)
