;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.5.4 Fixed-Size Data / Adding Structure / Defining Structure Types
;; Bouncing balls - ex. 67

#lang htdp/bsl

(define SPEED 3)
(define-struct balld [location direction])
(make-balld 10 "up")

(make-balld 0 "down")
(make-balld 30 "left")
