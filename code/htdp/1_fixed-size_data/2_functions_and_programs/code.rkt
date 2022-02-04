;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.2 Fixed-Size Data / Functions and Programs
;; Exercises 11-28

#lang htdp/bsl

(require 2htdp/image)

;; * Exercise 11

(define (distance x y)
  (sqrt (+ (sqr x)
           (sqr y))))

;; * Exercise 12

(define (cvolume x)
  (expt x 3))

(define (csurface x)
  (* 6 (sqr x)))

;; * Exercise 13

(define (string-empty? str)
  (= 0 (string-length str)))

(define (string-first str)
  (if (string-empty? str)
      ""
      (string-ith str 0)))

;; * Exercise 14

(define (string-last str)
  (if (string-empty? str)
      ""
      (string-ith str (sub1 (string-length str)))))

;; * Exercise 15

(define (==> sunny friday)
  (or (not sunny) friday))

;; * Exercise 16

(define (image-area image)
  (* (image-width image)
     (image-height image)))

;; * Exercise 17

(define (image-classify image)
  (cond
    [(> (image-height image) (image-width image))
     "tall"]
    [(= (image-height image) (image-width image))
     "square"]
    [else "wide"]))

;; * Exercise 18

(define (string-join str1 str2)
  (string-append str1 "_" str2))

;; * Exercise 19

(define (string-insert str i)
  (string-append
   (substring str 0 i)
   "_"
   (substring str i)))

;; * Exercise 20

(define (string-delete str i)
  (string-append
   (substring str 0 i)
   "_"
   (substring str (add1 i))))

;; * Exercise 21

(define (ff a)
  (* 10 a))

(ff (ff 1))
(+ (ff 1) (ff 1))

;; * Exercise 22

(define (distance-to-origin x y)
  (sqrt (+ (sqr x) (sqr y))))

(distance-to-origin 3 4)

;; * Exercise 23

(define (string-first s)
  (substring s 0 1))

;; substring just takes the first character ¯\_(ツ)_/¯

;; * Exercise 24

(define (==> x y)
  (or (not x) y))

;; (==> #true #false)
;; (or (not #true) #false)
;; (or #false #false)
;; #false

;; * Exercise 25

(define (image-classify-flawed img)
  (cond
    [(>= (image-height img) (image-width img))
     "tall"]
    [(= (image-height img) (image-width img))
     "square"]
    [(<= (image-height img) (image-width img))
     "wide"]))

(image-classify-flawed (square 3 "solid" "red"))

(define (image-classify-correct img)
  (cond
    [(> (image-height img) (image-width img))
     "tall"]
    [(= (image-height img) (image-width img))
     "square"]
    [(< (image-height img) (image-width img))
     "wide"]))

(image-classify-correct (square 3 "solid" "red"))

;; Stepping helps to see the intermediate products and makes the
;; flawed comparison stand out: (>= 6 6) => tall

;; * Exercise 26

(define (string-insert s i)
  (string-append (substring s 0 i)
                 "_"
                 (substring s i)))

;; This should insert "_" at the position: :white-check-mark:
(string-insert "helloworld" 6)

;; * Exercise 27 & 28

(define STANDARD-ATTENDANCE 120)
(define STANDARD-PRICE 5.0)
(define PRICE-STEP 0.1)
(define ATTENDANCE-STEP 15)
(define FIXED-COST 180.00)
(define PRICE-PER-ATTENDEE 0.04)

(define (attendees ticket-price)
  (- STANDARD-ATTENDANCE
     (* (/ (- ticket-price STANDARD-PRICE) PRICE-STEP)
        ATTENDANCE-STEP)))

(define (revenue ticket-price)
  (* ticket-price (attendees ticket-price)))

(define (cost ticket-price)
  (+ FIXED-COST (* PRICE-PER-ATTENDEE (attendees ticket-price))))

(define (profit ticket-price)
  (- (revenue ticket-price)
     (cost ticket-price)))

(define (profit-ugly ticket-price)
  (- (* (- 120
           (* (/ (- ticket-price 5.0)
                 0.1)
              15))
        ticket-price)
     (+ 180
        (* 0.04
           (- 120
              (* (/ (- ticket-price 5.0)
                    0.1)
                 15))))))

(profit 1)
(profit-ugly 1)
(profit 2)
(profit-ugly 2)
(profit 3)
(profit-ugly 3)
(profit 4)
(profit-ugly 4)
(profit 5)
(profit-ugly 5)

(profit 2.9)
(profit-ugly 2.9)
