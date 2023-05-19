import requests
import os
import bs4


url = 'http://xkcd.com'
os.makedirs('data/xkcd', exist_ok=True)

while not url.endswith('#'):
    # TODO: Download the page.
    print('Downloading page %s' % url)

    res = requests.get(url)
    res.raise_for_status()
    soup = bs4.BeautifulSoup(res.text, 'html5lib')

    # TODO: Find the URL of the comic image.
    comicElem = soup.select('#comic img')

    if len(comicElem) == 0:
        print('Could not find comic image.')
    else:
        # TODO: Download the image.
        comicUrl = 'http:' + comicElem[0].get('src')
        res = requests.get(comicUrl)
        res.raise_for_status()

        # TODO: Save the image to ./xkcd.
        imageFile = open(os.path.join('data/xkcd', os.path.basename(comicUrl)), 'wb')

        for chunk in res.iter_content(100000):
            imageFile.write(chunk)

        imageFile.close()

        # TODO: Get the Prev button's url.
        prevLink = soup.select('a[rel="prev"]')[0]
        url = 'http://xkcd.com' + prevLink.get('href')

print('Done.')
