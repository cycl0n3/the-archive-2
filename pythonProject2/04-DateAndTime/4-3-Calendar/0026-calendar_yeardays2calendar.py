import pprint
import calendar

cal = calendar.TextCalendar(calendar.SUNDAY)

cal_data = cal.yeardays2calendar(2017, 3)
print('len(cal_data):', len(cal_data))

top_months = cal_data[0]
print('len(top_months):', len(top_months))

first_month = top_months[0]
print('len(first_month):', len(first_month))

print('first_month:')
pprint.pprint(first_month)
