import org.yaml.snakeyaml.Yaml

Yaml yaml = new Yaml()
def raw = '''---
Time: 2001-11-23 15:01:42 -5
User: ed
Warning:
  This is an error message
  for the log file
---
Time: 2001-11-23 15:02:31 -5
User: ed
Warning:
  A slightly different error
  message.
---
Date: 2001-11-23 15:03:17 -5
User: ed
Fatal:
  Unknown variable "bar"
Stack:
  - file: TopClass.py
    line: 23
    code: |
      x = MoreObject("345\\n")
  - file: MoreClass.py
    line: 58
    code: |-
      foo = bar'''

job("yaml") {
    steps {
        for (def document : yaml.loadAll(raw)) {
            shell document.toString()
        }
    }
}