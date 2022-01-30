(in-package #:agitsc)

;; * Ex. 02

(/ (+ 8 12) 2)

;; * Ex. 03

(+ (* 3 3) (* 4 4))

;; * Ex. 05

(defun half (n)
  (/ n 2))

(defun cube (n)
  (* n n n))

(defun onemorep (x y)
  (equal x (+ y 1)))

;; * Ex. 06

(defun square (x)
  (* x x))

(defun pythag (x y)
  (sqrt (+ (square x)
           (square y))))

;; * Ex. 07

(defun miles-per-gallon (initial-reading final-reading gallons-consumed)
  (/ (- final-reading initial-reading) gallons-consumed))

;; * Ex. 09

(equal
 (cons 5 (list 6 7))
 '(5 6 7))

(equal
 (cons 5 '(list 6 7))
 '(5 list 6 7))

(equal
 (list 3 'from 9 'gives (- 9 3))
 '(3 from 9 gives 6))

(equal
 (+ (length '(1 foo 2 moo))
    (third '(1 foo 2 moo)))
 6)

(equal
 (rest '(cons is short for construct))
 '(is short for construct))

;; * Ex. 10

;; (third (the quick brown fox)) -> missing quote
(third '(the quick brown fox))

;; (list 2 and 2 is 4) -> missing quotes
(list 2 'and 2 'is 4)

;; (+ 1 '(length (list t t t t))) -> extra quote
(+ 1 (length (list t t t t)))

;; (cons 'patrick (seymour marvin)) -> missing quote
(cons 'patrick '(seymour marvin))

;; (cons 'patrick (list seymour marvin)) -> missing quotes
(cons 'patrick (list 'seymour 'marvin))

;; * Ex. 11

(defun longer-than (x y)
  (> (length x) (length y)))

;; * Ex. 12

(defun addlength (list)
  (cons (length list) list))
