;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.2.5 Fixed-Size Data / Functions and Programs / Programs
;; Exercise 31

#lang htdp/bsl

(require 2htdp/batch-io)

(define (letter fst lst signature-name)
  (string-append
   (opening fst)
   "\n\n"
   (body fst lst)
   "\n\n"
   (closing signature-name)))

(define (opening fst)
  (string-append "Dear " fst ","))

(define (body fst lst)
  (string-append
   "We have discovered that all people with the" "\n"
   "last name " lst " have won our lottery. So, " "\n"
   fst ", " "hurry and pick up your prize."))

(define (closing signature-name)
  (string-append
   "Sincerely,"
   "\n\n"
   signature-name
   "\n"))

(write-file 'stdout (letter "Anthony" "Le Cigne" "The President"))

(define (main in-fst in-lst in-signature out)
  (write-file out
              (letter (read-file in-fst)
                      (read-file in-lst)
                      (read-file in-signature))))

(main
 "files/first.txt"
 "files/last.txt"
 "files/signature.txt"
 "files/letter.txt")

;; see files/letter.txt
;;
;; Dear Anthony,

;; We have discovered that all people with the
;; last name Le Cigne have won our lottery. So,
;; Anthony, hurry and pick up your prize.

;; Sincerely,

;; The President
