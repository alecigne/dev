#!/bin/bash

set -e

# cd in script's directory
script_path=$(cd "$(dirname "${BASH_SOURCE[0]}")" || return ; pwd -P)
cd "${script_path}" || return

# Set or ask for variables
read -r -p "Enter project code number: " project_code
read -r -p "Enter project name: " project_name
filename="${project_code}_${project_name}.org"
org_timestamp="[$(date +"%Y-%m-%d %a")]"

# Creating the new kata branch
git checkout -b "${project_code}"

# Creating the kata file
tee -a "./wiki/katas/${filename}" << END
#+TITLE: [${project_code^^}] =${project_name}=

* Kata

- Start date :: ${org_timestamp}

- Content :: <insert-here>

- Project :: <insert-here>

- Branch :: [[https://github.com/alecigne/coding-katas/commits/${project_code}][${project_code}]]
END

# Adding the kata to the index
org_line="- [[[file:katas/${filename}][${project_code^^}]]] =${project_name}= :: Init. ${org_timestamp}: <insert-here>\n"
sed -i "/Perspectives/i ${org_line}" ./wiki/README.org
