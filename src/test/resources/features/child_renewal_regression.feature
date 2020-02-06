Feature: Passport Renewal application journey regression tests for children

  These are the regression tests for passport renewal applications for children

  @2REN @regression @renewal @child
  Scenario Outline: Child applicants can apply for a first time or renewal passport

    Given an applicant of type <applicant type>
    When they apply for a overseas renewal passport

    Examples: Only permitted child applications can apply to renew their passport
      | applicant type                    |
      | child from France                 |
