#!/bin/bash

set -e

# cd in script's directory
script_path=$(cd "$(dirname "${BASH_SOURCE[0]}")" || return ; pwd -P)
cd "${script_path}" || return

# Set or ask for variables
read -r -p "Enter project name: " project_name
filename="${project_name}.org"
org_timestamp="[$(date +"%Y-%m-%d %a")]"

# Creating the kata file
tee -a "./notes/katas/${filename}" << END
#+TITLE: =${project_name}=

* Project

- Start date :: ${org_timestamp}

- Content :: <insert-here>

- Code :: <insert-here>
END

# Adding the kata to the index
org_line="\n- [[[file:${filename}][${project_name}]]] :: Init. ${org_timestamp}: <insert-here>"
echo -e "${org_line}" >> ./notes/katas/index.org
