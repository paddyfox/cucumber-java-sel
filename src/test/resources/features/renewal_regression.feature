Feature: Passport Renewal application journey regression tests

  These are the regression tests for passport renewal applications

  @1REN @regression @renewal @adult
  Scenario Outline: Adult applicants can apply for a first time or renewal passport

    Given an applicant of type <applicant type>
    When they apply for a UK first time passport

    Examples: Only permitted adult applications can apply to renew their passport
      | applicant type                    |
      | adult from United Kingdom         |


  @2REN @regression @renewal @child
  Scenario Outline: Child applicants can apply for a first time or renewal passport

    Given an applicant of type <applicant type>
    When they apply for a overseas renewal passport

    Examples: Only permitted child applications can apply to renew their passport
      | applicant type                    |
      | child from France                 |
