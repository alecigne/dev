;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.2 Fixed-Size Data / Functions and Programs
;; Exercise 30

#lang htdp/bsl

(define STANDARD-ATTENDANCE 120)
(define STANDARD-PRICE 5.0)
(define PRICE-STEP 0.1)
(define ATTENDANCE-STEP 15)
(define PRICE-PER-ATTENDEE 1.50)
(define PRICE-SENSITIVITY (/ ATTENDANCE-STEP PRICE-STEP))

(define (attendees ticket-price)
  (- STANDARD-ATTENDANCE
     (* (- ticket-price STANDARD-PRICE)
        PRICE-SENSITIVITY)))

(define (revenue ticket-price)
  (* ticket-price (attendees ticket-price)))

(define (cost ticket-price)
  (* PRICE-PER-ATTENDEE (attendees ticket-price)))

(define (profit ticket-price)
  (- (revenue ticket-price)
     (cost ticket-price)))

(profit 3)
(profit 4)
(profit 5)
