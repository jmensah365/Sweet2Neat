- Home Page
    1) Buttons must have discernible text: https://dequeuniversity.com/rules/axe/3.5/button-name?application\u003daxeAPI
        a) [".css-78trlr-MuiButtonBase-root-MuiIconButton-root"] -> ~RESOLVED~
            Fix any of the following:
            Element does not have inner text that is visible to screen readers
            aria-label attribute does not exist or is empty
            aria-labelledby attribute does not exist, references elements that do not exist or references elements that are empty
            Element\u0027s default semantics were not overridden with role\u003d"presentation"
            Element\u0027s default semantics were not overridden with role\u003d"none"
            Element has no title attribute or the title attribute is empty


    2) Elements must have sufficient color contrast: https://dequeuniversity.com/rules/axe/3.5/color-contrast?application\u003daxeAPI
        a) ["#\\\\:r1\\\\:-label"]
            Fix any of the following:
            Element has insufficient color contrast of 2.86 (foreground color: #2c2a4b, background color: #6f69bb, font size: 12.0pt (16px), font weight: normal). Expected contrast ratio of 4.5:1


        b) ["#\\\\:r1\\\\:"]
            Fix any of the following:
            Element has insufficient color contrast of 4.03 (foreground color: #0e0e18, background color: #6f69bb, font size: 12.0pt (16px), font weight: normal). Expected contrast ratio of 4.5:1


    3) Document must not have more than one main landmark: https://dequeuniversity.com/rules/axe/3.5/landmark-no-duplicate-main?application\u003daxeAPI
        a) [".css-gg705p-MuiContainer-root"] -> ~RESOLVED~
            Fix any of the following:
            Document has more than one main landmark


    4) Ensures landmarks are unique: https://dequeuniversity.com/rules/axe/3.5/landmark-unique?application\u003daxeAPI
        a) [".css-gg705p-MuiContainer-root"] -> ~RESOLVED~
            Fix any of the following:
            The landmark must have a unique aria-label, aria-labelledby, or title to make landmarks distinguishable


    5) Page must contain a level-one heading: https://dequeuniversity.com/rules/axe/3.5/page-has-heading-one?application\u003daxeAPI
        a) ["html"]
            Fix all of the following:
            Page must have a level-one heading


    6) Ensure that scrollable region has keyboard access: https://dequeuniversity.com/rules/axe/3.5/scrollable-region-focusable?application\u003daxeAPI
        a) [".css-nd8f9a"]
            Fix any of the following:
            Element should have focusable content
            Element should be focusable

- Candy Inventory Page
    1) Certain ARIA roles must contain particular children: https://dequeuniversity.com/rules/axe/3.5/aria-required-children?application\u003daxeAPI

        a) ["#candyTypeSelect"]
            Fix any of the following:
            Required ARIA child role not present: textbox


        b) ["#\\\\:ri\\\\:"]
            Fix any of the following:
            Required ARIA child role not present: textbox


    2) Buttons must have discernible text: https://dequeuniversity.com/rules/axe/3.5/button-name?application\u003daxeAPI

        a) [".MuiInputAdornment-root \u003e .css-78trlr-MuiButtonBase-root-MuiIconButton-root.MuiIconButton-root.MuiIconButton-sizeMedium"]
            Fix any of the following:
            Element does not have inner text that is visible to screen readers
            aria-label attribute does not exist or is empty
            aria-labelledby attribute does not exist, references elements that do not exist or references elements that are empty
            Element\u0027s default semantics were not overridden with role\u003d"presentation"
            Element\u0027s default semantics were not overridden with role\u003d"none"
            Element has no title attribute or the title attribute is empty


        b) [".MuiButton-containedSizeMedium"]
            Fix any of the following:
            Element does not have inner text that is visible to screen readers
            aria-label attribute does not exist or is empty
            aria-labelledby attribute does not exist, references elements that do not exist or references elements that are empty
            Element\u0027s default semantics were not overridden with role\u003d"presentation"
            Element\u0027s default semantics were not overridden with role\u003d"none"
            Element has no title attribute or the title attribute is empty


    3) Elements must have sufficient color contrast: https://dequeuniversity.com/rules/axe/3.5/color-contrast?application\u003daxeAPI

        a) ["#\\\\:r1\\\\:-label"]
            Fix any of the following:
            Element has insufficient color contrast of 2.86 (foreground color: #2c2a4b, background color: #6f69bb, font size: 12.0pt (16px), font weight: normal). Expected contrast ratio of 4.5:1


        b) ["#\\\\:r1\\\\:"]
            Fix any of the following:
            Element has insufficient color contrast of 4.03 (foreground color: #0e0e18, background color: #6f69bb, font size: 12.0pt (16px), font weight: normal). Expected contrast ratio of 4.5:1


    4) Document must have one main landmark: https://dequeuniversity.com/rules/axe/3.5/landmark-one-main?application\u003daxeAPI

        a) ["html"]
            Fix all of the following:
            Document does not have a main landmark


    5) Page must contain a level-one heading: https://dequeuniversity.com/rules/axe/3.5/page-has-heading-one?application\u003daxeAPI

        a) ["html"]
            Fix all of the following:
            Page must have a level-one heading


    6) All page content must be contained by landmarks: https://dequeuniversity.com/rules/axe/3.5/region?application\u003daxeAPI

        a) ["#\\\\:r5\\\\:-label"]
            Fix any of the following:
            Some page content is not contained by landmarks


        b) ["#\\\\:r5\\\\:"]
            Fix any of the following:
            Some page content is not contained by landmarks


        c) ["#selectCandyType"]
            Fix any of the following:
            Some page content is not contained by landmarks


        d) ["#candyTypeSelect"]
            Fix any of the following:
            Some page content is not contained by landmarks


        e) ["#\\\\:r9\\\\:-label"]
            Fix any of the following:
            Some page content is not contained by landmarks


        f) ["#\\\\:r9\\\\:"]
            Fix any of the following:
            Some page content is not contained by landmarks


        g) ["#\\\\:rb\\\\:-label"]
            Fix any of the following:
            Some page content is not contained by landmarks


        h) ["#\\\\:rb\\\\:"]
            Fix any of the following:
            Some page content is not contained by landmarks


        i) ["#\\\\:rd\\\\:-label"]
            Fix any of the following:
            Some page content is not contained by landmarks


        j) ["#\\\\:rd\\\\:"]
            Fix any of the following:
            Some page content is not contained by landmarks


        k) [".MuiButton-containedSizeMedium"]
            Fix any of the following:
            Some page content is not contained by landmarks


        l) ["h4"]
            Fix any of the following:
            Some page content is not contained by landmarks


        m) ["table"]
            Fix any of the following:
            Some page content is not contained by landmarks


        n) ["#\\\\:rj\\\\:"]
            Fix any of the following:
            Some page content is not contained by landmarks


        o) ["#\\\\:ri\\\\:"]
            Fix any of the following:
            Some page content is not contained by landmarks


        p) [".MuiTablePagination-displayedRows"]
            Fix any of the following:
            Some page content is not contained by landmarks


        q) [".MuiTablePagination-actions"]
            Fix any of the following:
            Some page content is not contained by landmark

- Candy Categories page
    1) Buttons must have discernible text: https://dequeuniversity.com/rules/axe/3.5/button-name?application\u003daxeAPI

        a) [".css-78trlr-MuiButtonBase-root-MuiIconButton-root"]
            Fix any of the following:
            Element does not have inner text that is visible to screen readers
            aria-label attribute does not exist or is empty
            aria-labelledby attribute does not exist, references elements that do not exist or references elements that are empty
            Element\u0027s default semantics were not overridden with role\u003d"presentation"
            Element\u0027s default semantics were not overridden with role\u003d"none"
            Element has no title attribute or the title attribute is empty


    2) Elements must have sufficient color contrast: https://dequeuniversity.com/rules/axe/3.5/color-contrast?application\u003daxeAPI

        a) ["#\\\\:r1\\\\:-label"]
            Fix any of the following:
            Element has insufficient color contrast of 2.86 (foreground color: #2c2a4b, background color: #6f69bb, font size: 12.0pt (16px), font weight: normal). Expected contrast ratio of 4.5:1


        b) ["#\\\\:r1\\\\:"]
            Fix any of the following:
            Element has insufficient color contrast of 4.03 (foreground color: #0e0e18, background color: #6f69bb, font size: 12.0pt (16px), font weight: normal). Expected contrast ratio of 4.5:1


    3) Page must contain a level-one heading: https://dequeuniversity.com/rules/axe/3.5/page-has-heading-one?application\u003daxeAPI

        a) ["html"]
            Fix all of the following:
            Page must have a level-one heading

- Warehouse Page
    1) Certain ARIA roles must contain particular children: https://dequeuniversity.com/rules/axe/3.5/aria-required-children?application\u003daxeAPI

        a) ["#\\\\:rc\\\\:"]
            Fix any of the following:
            Required ARIA child role not present: textbox


    2) Buttons must have discernible text: https://dequeuniversity.com/rules/axe/3.5/button-name?application\u003daxeAPI

        a) [".MuiInputAdornment-root \u003e .css-78trlr-MuiButtonBase-root-MuiIconButton-root.MuiIconButton-root.MuiIconButton-sizeMedium"]
            Fix any of the following:
            Element does not have inner text that is visible to screen readers
            aria-label attribute does not exist or is empty
            aria-labelledby attribute does not exist, references elements that do not exist or references elements that are empty
            Element\u0027s default semantics were not overridden with role\u003d"presentation"
            Element\u0027s default semantics were not overridden with role\u003d"none"
            Element has no title attribute or the title attribute is empty


    3) Elements must have sufficient color contrast: https://dequeuniversity.com/rules/axe/3.5/color-contrast?application\u003daxeAPI

        a) ["#\\\\:r1\\\\:-label"]
            Fix any of the following:
            Element has insufficient color contrast of 2.86 (foreground color: #2c2a4b, background color: #6f69bb, font size: 12.0pt (16px), font weight: normal). Expected contrast ratio of 4.5:1


        b) ["#\\\\:r1\\\\:"]
            Fix any of the following:
            Element has insufficient color contrast of 4.03 (foreground color: #0e0e18, background color: #6f69bb, font size: 12.0pt (16px), font weight: normal). Expected contrast ratio of 4.5:1


    4) Document must have one main landmark: https://dequeuniversity.com/rules/axe/3.5/landmark-one-main?application\u003daxeAPI

        a) ["html"]
            Fix all of the following:
            Document does not have a main landmark


    5) Page must contain a level-one heading: https://dequeuniversity.com/rules/axe/3.5/page-has-heading-one?application\u003daxeAPI

        a) ["html"]
            Fix all of the following:
            Page must have a level-one heading


    6) All page content must be contained by landmarks: https://dequeuniversity.com/rules/axe/3.5/region?application\u003daxeAPI

        a) ["#\\\\:r5\\\\:-label"]
            Fix any of the following:
            Some page content is not contained by landmarks


        b) ["#\\\\:r5\\\\:"]
            Fix any of the following:
            Some page content is not contained by landmarks


        c) ["#\\\\:r7\\\\:-label"]
            Fix any of the following:
            Some page content is not contained by landmarks


        d) ["#\\\\:r7\\\\:"]
            Fix any of the following:
            Some page content is not contained by landmarks


        e) [".MuiButton-containedSizeMedium"]
            Fix any of the following:
            Some page content is not contained by landmarks


        f) ["h4"]
            Fix any of the following:
            Some page content is not contained by landmarks


        g) ["table"]
            Fix any of the following:
            Some page content is not contained by landmarks


        h) ["#\\\\:rd\\\\:"]
            Fix any of the following:
            Some page content is not contained by landmarks


        i) ["#\\\\:rc\\\\:"]
            Fix any of the following:
            Some page content is not contained by landmarks


        j) [".MuiTablePagination-displayedRows"]
            Fix any of the following:
            Some page content is not contained by landmarks


        k) [".MuiTablePagination-actions"]
            Fix any of the following:
            Some page content is not contained by landmarks

- Stock Page
1) Certain ARIA roles must contain particular children: https://dequeuniversity.com/rules/axe/3.5/aria-required-children?application\u003daxeAPI
  a) ["#candyIdSelect"]
    Fix any of the following:
      Required ARIA child role not present: textbox


  b) ["#warehouseIdSelect"]
    Fix any of the following:
      Required ARIA child role not present: textbox


  c) ["#\\\\:re\\\\:"]
    Fix any of the following:
      Required ARIA child role not present: textbox


2) Buttons must have discernible text: https://dequeuniversity.com/rules/axe/3.5/button-name?application\u003daxeAPI
  a) [".MuiInputAdornment-root \u003e .css-78trlr-MuiButtonBase-root-MuiIconButton-root.MuiIconButton-root.MuiIconButton-sizeMedium"]
    Fix any of the following:
      Element does not have inner text that is visible to screen readers
      aria-label attribute does not exist or is empty
      aria-labelledby attribute does not exist, references elements that do not exist or references elements that are empty
      Element\u0027s default semantics were not overridden with role\u003d"presentation"
      Element\u0027s default semantics were not overridden with role\u003d"none"
      Element has no title attribute or the title attribute is empty


3) Elements must have sufficient color contrast: https://dequeuniversity.com/rules/axe/3.5/color-contrast?application\u003daxeAPI
  a) ["#\\\\:r1\\\\:-label"]
    Fix any of the following:
      Element has insufficient color contrast of 2.86 (foreground color: #2c2a4b, background color: #6f69bb, font size: 12.0pt (16px), font weight: normal). Expected contrast ratio of 4.5:1


  b) ["#\\\\:r1\\\\:"]
    Fix any of the following:
      Element has insufficient color contrast of 4.03 (foreground color: #0e0e18, background color: #6f69bb, font size: 12.0pt (16px), font weight: normal). Expected contrast ratio of 4.5:1


4) Document must have one main landmark: https://dequeuniversity.com/rules/axe/3.5/landmark-one-main?application\u003daxeAPI
  a) ["html"]
    Fix all of the following:
      Document does not have a main landmark


5) Page must contain a level-one heading: https://dequeuniversity.com/rules/axe/3.5/page-has-heading-one?application\u003daxeAPI
  a) ["html"]
    Fix all of the following:
      Page must have a level-one heading


6) All page content must be contained by landmarks: https://dequeuniversity.com/rules/axe/3.5/region?application\u003daxeAPI
  a) ["#selectCandyId"]
    Fix any of the following:
      Some page content is not contained by landmarks


  b) ["#candyIdSelect"]
    Fix any of the following:
      Some page content is not contained by landmarks


  c) ["#selectWarehouseId"]
    Fix any of the following:
      Some page content is not contained by landmarks


  d) ["#warehouseIdSelect"]
    Fix any of the following:
      Some page content is not contained by landmarks


  e) ["#\\\\:r9\\\\:-label"]
    Fix any of the following:
      Some page content is not contained by landmarks


  f) ["#\\\\:r9\\\\:"]
    Fix any of the following:
      Some page content is not contained by landmarks


  g) [".MuiButton-containedSizeMedium"]
    Fix any of the following:
      Some page content is not contained by landmarks


  h) ["h4"]
    Fix any of the following:
      Some page content is not contained by landmarks


  i) ["table"]
    Fix any of the following:
      Some page content is not contained by landmarks


  j) ["#\\\\:rf\\\\:"]
    Fix any of the following:
      Some page content is not contained by landmarks


  k) ["#\\\\:re\\\\:"]
    Fix any of the following:
      Some page content is not contained by landmarks


  l) [".MuiTablePagination-displayedRows"]
    Fix any of the following:
      Some page content is not contained by landmarks


  m) [".MuiTablePagination-actions"]
    Fix any of the following:
      Some page content is not contained by landmarks

- Order List page
    1) Certain ARIA roles must contain particular children: https://dequeuniversity.com/rules/axe/3.5/aria-required-children?application\u003daxeAPI

        a) ["#statusSelect"]
            Fix any of the following:
            Required ARIA child role not present: textbox


        b) ["#\\\\:re\\\\:"]
            Fix any of the following:
            Required ARIA child role not present: textbox


    2) Buttons must have discernible text: https://dequeuniversity.com/rules/axe/3.5/button-name?application\u003daxeAPI

        a) [".css-78trlr-MuiButtonBase-root-MuiIconButton-root"]
            Fix any of the following:
            Element does not have inner text that is visible to screen readers
            aria-label attribute does not exist or is empty
            aria-labelledby attribute does not exist, references elements that do not exist or references elements that are empty
            Element\u0027s default semantics were not overridden with role\u003d"presentation"
            Element\u0027s default semantics were not overridden with role\u003d"none"
            Element has no title attribute or the title attribute is empty


    3) Elements must have sufficient color contrast: https://dequeuniversity.com/rules/axe/3.5/color-contrast?application\u003daxeAPI

        a) ["#\\\\:r1\\\\:-label"]
            Fix any of the following:
            Element has insufficient color contrast of 2.86 (foreground color: #2c2a4b, background color: #6f69bb, font size: 12.0pt (16px), font weight: normal). Expected contrast ratio of 4.5:1


        b) ["#\\\\:r1\\\\:"]
            Fix any of the following:
            Element has insufficient color contrast of 4.03 (foreground color: #0e0e18, background color: #6f69bb, font size: 12.0pt (16px), font weight: normal). Expected contrast ratio of 4.5:1


    4) Document must have one main landmark: https://dequeuniversity.com/rules/axe/3.5/landmark-one-main?application\u003daxeAPI

        a) ["html"]
            Fix all of the following:
            Document does not have a main landmark


    5) Page must contain a level-one heading: https://dequeuniversity.com/rules/axe/3.5/page-has-heading-one?application\u003daxeAPI

        a) ["html"]
            Fix all of the following:
            Page must have a level-one heading


    6) All page content must be contained by landmarks: https://dequeuniversity.com/rules/axe/3.5/region?application\u003daxeAPI

        a) ["#\\\\:r5\\\\:-label"]
            Fix any of the following:
            Some page content is not contained by landmarks


        b) ["#\\\\:r5\\\\:"]
            Fix any of the following:
            Some page content is not contained by landmarks


        c) ["#selectStatus"]
            Fix any of the following:
            Some page content is not contained by landmarks


        d) ["#statusSelect"]
            Fix any of the following:
            Some page content is not contained by landmarks


        e) ["#\\\\:r9\\\\:-label"]
            Fix any of the following:
            Some page content is not contained by landmarks


        f) ["#\\\\:r9\\\\:"]
            Fix any of the following:
            Some page content is not contained by landmarks


        g) [".MuiButton-containedSizeMedium"]
            Fix any of the following:
            Some page content is not contained by landmarks


        h) ["h4"]
            Fix any of the following:
            Some page content is not contained by landmarks


        i) ["table"]
            Fix any of the following:
            Some page content is not contained by landmarks


        j) ["#\\\\:rf\\\\:"]
            Fix any of the following:
            Some page content is not contained by landmarks


        k) ["#\\\\:re\\\\:"]
            Fix any of the following:
            Some page content is not contained by landmarks


        l) [".MuiTablePagination-displayedRows"]
            Fix any of the following:
            Some page content is not contained by landmarks


        m) [".MuiTablePagination-actions"]
            Fix any of the following:
            Some page content is not contained by landmarks

- Order Info Page
    1) Certain ARIA roles must contain particular children: https://dequeuniversity.com/rules/axe/3.5/aria-required-children?application\u003daxeAPI

        a) ["#orderIdSelect"]
            Fix any of the following:
            Required ARIA child role not present: textbox


        b) ["#candySelect"]
            Fix any of the following:
            Required ARIA child role not present: textbox


        c) ["#\\\\:rg\\\\:"]
            Fix any of the following:
            Required ARIA child role not present: textbox


    2) Buttons must have discernible text: https://dequeuniversity.com/rules/axe/3.5/button-name?application\u003daxeAPI

        a) [".css-78trlr-MuiButtonBase-root-MuiIconButton-root"]
            Fix any of the following:
            Element does not have inner text that is visible to screen readers
            aria-label attribute does not exist or is empty
            aria-labelledby attribute does not exist, references elements that do not exist or references elements that are empty
            Element\u0027s default semantics were not overridden with role\u003d"presentation"
            Element\u0027s default semantics were not overridden with role\u003d"none"
            Element has no title attribute or the title attribute is empty


    3) Elements must have sufficient color contrast: https://dequeuniversity.com/rules/axe/3.5/color-contrast?application\u003daxeAPI

        a) ["#\\\\:r1\\\\:-label"]
            Fix any of the following:
            Element has insufficient color contrast of 2.86 (foreground color: #2c2a4b, background color: #6f69bb, font size: 12.0pt (16px), font weight: normal). Expected contrast ratio of 4.5:1


        b) ["#\\\\:r1\\\\:"]
            Fix any of the following:
            Element has insufficient color contrast of 4.03 (foreground color: #0e0e18, background color: #6f69bb, font size: 12.0pt (16px), font weight: normal). Expected contrast ratio of 4.5:1


    4) Document must have one main landmark: https://dequeuniversity.com/rules/axe/3.5/landmark-one-main?application\u003daxeAPI

        a) ["html"]
            Fix all of the following:
            Document does not have a main landmark


    5) Page must contain a level-one heading: https://dequeuniversity.com/rules/axe/3.5/page-has-heading-one?application\u003daxeAPI

        a) ["html"]
            Fix all of the following:
            Page must have a level-one heading


    6) All page content must be contained by landmarks: https://dequeuniversity.com/rules/axe/3.5/region?application\u003daxeAPI

        a) ["#selectOrderId"]
            Fix any of the following:
            Some page content is not contained by landmarks


        b) ["#orderIdSelect"]
            Fix any of the following:
            Some page content is not contained by landmarks


        c) ["#selectCandyId"]
            Fix any of the following:
            Some page content is not contained by landmarks


        d) ["#candySelect"]
            Fix any of the following:
            Some page content is not contained by landmarks


        e) ["#\\\\:r9\\\\:-label"]
            Fix any of the following:
            Some page content is not contained by landmarks


        f) ["#\\\\:r9\\\\:"]
            Fix any of the following:
            Some page content is not contained by landmarks


        g) ["#\\\\:rb\\\\:-label"]
            Fix any of the following:
            Some page content is not contained by landmarks


        h) ["#\\\\:rb\\\\:"]
            Fix any of the following:
            Some page content is not contained by landmarks


        i) [".MuiButton-containedSizeMedium"]
            Fix any of the following:
            Some page content is not contained by landmarks


        j) ["h4"]
            Fix any of the following:
            Some page content is not contained by landmarks


        k) ["table"]
            Fix any of the following:
            Some page content is not contained by landmarks


        l) ["#\\\\:rh\\\\:"]
            Fix any of the following:
            Some page content is not contained by landmarks


        m) ["#\\\\:rg\\\\:"]
            Fix any of the following:
            Some page content is not contained by landmarks


        n) [".MuiTablePagination-displayedRows"]
            Fix any of the following:
            Some page content is not contained by landmarks


        o) [".MuiTablePagination-actions"]
            Fix any of the following:
            Some page content is not contained by landmarks

- About Page
    1) Buttons must have discernible text: https://dequeuniversity.com/rules/axe/3.5/button-name?application\u003daxeAPI

        a) [".css-78trlr-MuiButtonBase-root-MuiIconButton-root"]
            Fix any of the following:
            Element does not have inner text that is visible to screen readers
            aria-label attribute does not exist or is empty
            aria-labelledby attribute does not exist, references elements that do not exist or references elements that are empty
            Element\u0027s default semantics were not overridden with role\u003d"presentation"
            Element\u0027s default semantics were not overridden with role\u003d"none"
            Element has no title attribute or the title attribute is empty


    2) Elements must have sufficient color contrast: https://dequeuniversity.com/rules/axe/3.5/color-contrast?application\u003daxeAPI

        a) ["#\\\\:r1\\\\:-label"]
            Fix any of the following:
            Element has insufficient color contrast of 2.86 (foreground color: #2c2a4b, background color: #6f69bb, font size: 12.0pt (16px), font weight: normal). Expected contrast ratio of 4.5:1


        b) ["#\\\\:r1\\\\:"]
            Fix any of the following:
            Element has insufficient color contrast of 4.03 (foreground color: #0e0e18, background color: #6f69bb, font size: 12.0pt (16px), font weight: normal). Expected contrast ratio of 4.5:1


        c) [".css-u6u9h1-MuiTypography-root"]
            Fix any of the following:
            Element has insufficient color contrast of 3.68 (foreground color: #e2e1f1, background color: #6f69bb, font size: 12.0pt (16px), font weight: normal). Expected contrast ratio of 4.5:1


        d) ["strong:nth-child(1)"]
            Fix any of the following:
            Element has insufficient color contrast of 3.68 (foreground color: #e2e1f1, background color: #6f69bb, font size: 12.0pt (16px), font weight: bold). Expected contrast ratio of 4.5:1


        e) ["strong:nth-child(2)"]
            Fix any of the following:
            Element has insufficient color contrast of 3.68 (foreground color: #e2e1f1, background color: #6f69bb, font size: 12.0pt (16px), font weight: bold). Expected contrast ratio of 4.5:1


    3) Document must have one main landmark: https://dequeuniversity.com/rules/axe/3.5/landmark-one-main?application\u003daxeAPI

        a) ["html"]
            Fix all of the following:
            Document does not have a main landmark


    4) All page content must be contained by landmarks: https://dequeuniversity.com/rules/axe/3.5/region?application\u003daxeAPI

        a) [".css-19vq5pr"]
            Fix any of the following:
            Some page content is not contained by landmarks


        b) ["h2"]
            Fix any of the following:
            Some page content is not contained by landmarks


        c) ["li:nth-child(1) \u003e .MuiListItemText-root.css-tlelie-MuiListItemText-root"]
            Fix any of the following:
            Some page content is not contained by landmarks


        d) ["li:nth-child(2) \u003e .MuiListItemText-root.css-tlelie-MuiListItemText-root"]
            Fix any of the following:
            Some page content is not contained by landmarks


        e) ["li:nth-child(3) \u003e .MuiListItemText-root.css-tlelie-MuiListItemText-root"]
            Fix any of the following:
            Some page content is not contained by landmarks


        f) [".css-4sywta"]
            Fix any of the following:
            Some page content is not contained by landmark


