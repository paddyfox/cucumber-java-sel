Feature: Passport Renewal application journey regression tests

  These are the regression tests for passport renewal applications

  @1REN @regression @renewal @adult
  Scenario Outline: Adult applicants can apply for a first time or renewal passport

    Given an applicant of type <applicant type>
    When they apply for a <passport type> passport

    Examples: Only permitted adult applications can apply to renew their passport
      | applicant type                    | passport type       |
      | adult from United Kingdom         | adult renewal       |


  @2REN @regression @renewal @child
  Scenario Outline: Child applicants can apply for a first time or renewal passport

    Given an applicant of type <applicant type>
    When they apply for a <passport type> passport

    Examples: Only permitted child applications can apply to renew their passport
      | applicant type                    | passport type       |
      | child from United Kingdom         | child renewal       |
