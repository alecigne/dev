;;;; chapter13.lisp

(in-package #:chapter13)

(defun url-decode (char1 char2 &optional (default #\Space))
  "Decode an url character made of CHAR1 and CHAR2 to an ASCII character."
  (let ((code (parse-integer
               (coerce (list char1 char2) 'string)
               :radix 16
               :junk-allowed t)))
    (if code (code-char code) default)))

(defun decode-param (param)
  "Decode the query parameter PARAM."
  (labels
      ((f (lst)
         (when lst
           (case (car lst)
             (#\% (cons (url-decode (second lst) (third lst))
                        (f (cdddr lst))))
             (#\+ (cons #\Space (f (rest lst))))
             (otherwise (cons (first lst) (f (rest lst))))))))
    (coerce (f (coerce param 'list)) 'string)))

(defun parse-params (params)
  "Parse all parameters in PARAMS, a chain of query strings."
  (let ((i1 (position #\= params))
        (i2 (position #\& params)))
    (cond (i1 (cons (cons (intern (string-upcase (subseq params 0 i1)))
                          (decode-param (subseq params (1+ i1) i2)))
                    (and i2 (parse-params (subseq params (1+ i2))))))
          ((equal params "") nil)
          (t params))))

(defun parse-header (header)
  "Parse an HTTP request header."
  (let* ((url (subseq header
                      (+ 2 (position #\space header))
                      (position #\space header :from-end t)))
         (params-pos (position #\? url)))
    (if params-pos
        (cons (subseq url 0 params-pos) (parse-params (subseq url (1+ params-pos))))
        (cons url nil))))

(defun get-header (stream)
  "Parse the rest of a header in STREAM."
  (let* ((s (read-line stream))
         (h (let ((i (position #\: s)))
              (when i
                (cons (intern (string-upcase (subseq s 0 i)))
                      (subseq s (+ i 2)))))))
    (when h (cons h (get-header stream)))))
