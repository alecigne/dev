;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.2 Fixed-Size Data / Functions and Programs
;; Exercise 29

#lang htdp/bsl

(define STANDARD-ATTENDANCE 120)
(define STANDARD-PRICE 5.0)
(define PRICE-STEP 0.1)
(define ATTENDANCE-STEP 15)
(define PRICE-PER-ATTENDEE 1.50)

(define (attendees ticket-price)
  (- STANDARD-ATTENDANCE
     (* (/ (- ticket-price STANDARD-PRICE) PRICE-STEP)
        ATTENDANCE-STEP)))

(define (revenue ticket-price)
  (* ticket-price (attendees ticket-price)))

(define (cost ticket-price)
  (* PRICE-PER-ATTENDEE (attendees ticket-price)))

(define (profit ticket-price)
  (- (revenue ticket-price)
     (cost ticket-price)))

(define (profit-ugly ticket-price)
  (- (* (- 120
           (* (/ (- ticket-price 5.0)
                 0.1)
              15))
        ticket-price)
     (* 1.50
        (- 120
           (* (/ (- ticket-price 5.0)
                 0.1)
              15)))))

(profit 3)
(profit-ugly 3)
(profit 4)
(profit-ugly 4)
(profit 5)
(profit-ugly 5)
